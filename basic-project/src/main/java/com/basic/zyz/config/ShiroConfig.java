package com.basic.zyz.config;

import com.basic.zyz.common.constant.Constants;
import com.basic.zyz.common.utils.Encodes;
import com.basic.zyz.module.pojo.Role;
import com.basic.zyz.module.pojo.User;
import com.basic.zyz.module.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);

    @Autowired
    private UserService userService;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("anon", new AnonymousFilter());
//        filters.put("authc", formAuthenticationFilter);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/home", "authc");
        filterChainDefinitionMap.put("/page/**", "authc");
        //authc表示需要验证身份才能访问，还有一些比如anon表示不需要验证身份就能访问等。
//        如果未登录，登录页配置
        shiroFilterFactoryBean.setLoginUrl("/index");
//        登录成功跳转，项目用的是ajax，所以不需要配置
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(CacheManager shiroRedisCacheManager, ShiroSessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(shiroRedisCacheManager);
        securityManager.setRememberMeManager(cookieRememberMeManager());
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    /**
     * 登录时的验证
     * @return
     */
    @Bean
    public AuthorizingRealm myShiroRealm() {
        AuthorizingRealm myShiroRealm = new AuthorizingRealm() {

            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
                LOGGER.info("认证 --> MyShiroRealm.doGetAuthenticationInfo()");
                //获取用户的输入的账号.
                String phone = (String) token.getPrincipal();
                LOGGER.info("界面输入的手机号：{}", phone);
                //通过phone从数据库中查找 User对象，
                User user = userService.findUserByPhone(phone);
                if (user == null) {
                    //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
                    return null;
                }
                if (Constants.NO.equals(user.getState())) {
                    throw new AuthenticationException("msg:该已帐号禁止登录.");
                }
                byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                        user, //用户信息
                        user.getPassword().substring(16), //密码
                        ByteSource.Util.bytes(salt),    //密码的盐值，这里是用户密码解密后的数据
                        getName()  //realm name
                );
                return authenticationInfo;
            }

            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
                LOGGER.info("权限配置 --> MyShiroRealm.doGetAuthorizationInfo()");

                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                User user = (User) principal.getPrimaryPrincipal();
                List<Role> roles = userService.findListByUserId(user.getPhone());
                LOGGER.info("用户：{}, 角色有{}个", user.getPhone(), roles.size());
                roles.stream().forEach(
                        role -> {
                            authorizationInfo.addRole(role.getName());
                            /*permissionMapper.findPermissionByRoleId(role.getId()).stream().forEach(
                                    permission -> {
                                        authorizationInfo.addStringPermission(permission.getPermission());
                                    }
                            );*/
                        }
                );
                return authorizationInfo;
            }
        };
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
        matcher.setHashIterations(1024);
//        这个是为了验证密码是否一致
        myShiroRealm.setCredentialsMatcher(matcher); //设置加密规则
        myShiroRealm.setCachingEnabled(true);
        myShiroRealm.setAuthorizationCachingEnabled(true);
        //myShiroRealm.setAuthenticationCachingEnabled(true);
        return myShiroRealm;
    }

    // 需要与存储密码时的加密规则一致
//    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-1");       //散列算法:这里使用SHA-1算法;
        hashedCredentialsMatcher.setHashIterations(1024);          //散列的次数
        return hashedCredentialsMatcher;
    }

    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleMappingExceptionResolver resolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "error/403");
        exceptionResolver.setExceptionMappings(properties);
        return exceptionResolver;
    }

    //cookie对象;
    @Bean
    public SimpleCookie rememberMeCookie() {
        LOGGER.info("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");

        //<!-- 记住我cookie生效时间 ,单位秒;-->
        simpleCookie.setMaxAge(30 * 60);
        return simpleCookie;
    }

    //cookie管理对象;
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        LOGGER.info("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        return manager;
    }

}