package com.zyz.party.controller;

import com.alibaba.fastjson.JSONObject;
import com.zyz.party.util.HttpsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zyz
 * @date 2018/9/12
 */
@Controller
public class QQLoginController {

    /**
     * QQ鉴权
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/authqq")
    public void authQQ(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // QQ登录有点特殊，参数放在#后面，后台无法获取#后面的参数，只能用JS做中间转换
        String html = "<!DOCTYPE html>" +
                "<html lang=\"zh-cn\">" +
                "<head>" +
                "   <title>QQ登录重定向页</title>" +
                "   <meta charset=\"utf-8\"/>" +
                "</head>" +
                "<body>" +
                "   <script type=\"text/javascript\">" +
                "   location.href = location.href.replace('#', '&').replace('auth_qq', 'auth_qq_redirect');" +
                "   </script>" +
                "</body>" +
                "</html>";
        response.getWriter().print(html);
    }

    @RequestMapping("/getQQInfo")
    public String getQQInfo(String accessToken, String appId) {
        // 根据accessToken换取openId
        // 错误示例：callback( {"error":100016,"error_description":"access token check failed"} );
        // 正确示例：callback( {"client_id":"10XXXXX49","openid":"CF2XXXXXXXX9F4C"} );
        String result = HttpsUtil.get("https://graph.qq.com/oauth2.0/me?access_token=" + accessToken);
        Map<String, Object> resp = JSONObject.parseObject(result); // 这个方法就是把结果转Map

        Integer errorCode = (Integer) resp.get("error");
        String errorMsg = (String) resp.get("error_description");
        String openId = (String) resp.get("openid");
        if (errorCode != null) {
            return errorCode + "获取QQ用户openId失败：" + errorMsg;
        }

        // 获取用户昵称、头像等信息，{ret: 0, msg: '', nickname: '', ...} ret不为0表示失败
        result = HttpsUtil.get("https://graph.qq.com/user/get_user_info?access_token=" + accessToken + "&oauth_consumer_key=" + appId + "&openid=" + openId);
        resp = JSONObject.parseObject(result);

        Integer ret = (Integer) resp.get("ret");
        String msg = (String) resp.get("msg");
        if (ret != 0) {
            return "获取用户QQ信息失败：" + msg;
        }

        // 用户昵称可能存在4个字节的utf-8字符，MySQL默认不支持，直接插入会报错，所以过滤掉
//        String nickname = StringUtil.filterUtf8Mb4((String) resp.get("nickname")).trim(); // 这个方法可以自行百度
        // figureurl_qq_2=QQ的100*100头像，figureurl_2=QQ 100&100空间头像，QQ头像不一定有，空间头像一定有
        String avatar = (String) resp.get("figureurl_qq_2");
        if (StringUtils.isBlank(avatar)){
            avatar = (String) resp.get("figureurl_2");
        }
        String gender = (String) resp.get("gender");
        return gender;
    }

}
