package com.basic.zyz.common.config;

import com.basic.zyz.common.web.RedisRepository;
import com.basic.zyz.module.pojo.Dict;
import com.basic.zyz.module.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import java.util.ArrayList;
import java.util.List;


public class CustomerTagProcessor extends AbstractAttributeTagProcessor {

    private static final String DELIMITER = ",";
    private static final String ATTRIBUTE_NAME = "dict";// 标签名
    private static final int PRECEDENCE = 300;// 优先级

    /**
     * 查找字典数据
     */
    private static final String GET_LABEL_BY_TYPE = "getLabel";
    /**
     * 查找字典数据并输出下拉框html
     */
    private static final String SELECT_LABEL_BY_TYPE = "getSelect";
    /**
     * 查找字典数据并输出单选框html
     */
    private static final String RADIO_LABEL_BY_TYPE = "getRadio";
    /**
     * 查找字典数据并输出单选框html
     */
    private static final String CHECKBOX_LABEL_BY_TYPE = "getCheckBox";
    /**
     * 根据id获取用户名
     */
    private static final String USER_NAME_BY_TYPE = "getNameById";
    /**
     * 根据id获取手机号
     */
    private static final String USER_PHONE_BY_TYPE = "getPhoneById";
    /**
     * 获取当前用户的id
     */
    private static final String USER_ID_BY_TYPE = "getUser";

    public CustomerTagProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, // 此处理器将仅应用于HTML模式
                dialectPrefix, // 要应用于名称的匹配前缀
                null, // 标签名称：匹配此名称的特定标签
                false, // 将标签前缀应用于标签名称
                ATTRIBUTE_NAME, // 无属性名称：将通过标签名称匹配
                true, // 没有要应用于属性名称的前缀
                PRECEDENCE, true); // 优先(内部方言自己的优先)

    }

    /**
     * context 页面上下文 tag 标签
     * 获取标签内容表达式
     */
    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
                             String attributeValue, IElementTagStructureHandler structureHandler) {
        final String rawValue = ThymeleafFacade.getRawValue(tag, attributeName);
        String type = null;
        String exper = null;
        if (StringUtils.isNotBlank(rawValue)) {
            String[] tmp = rawValue.split(":");

            if (tmp.length > 0 && StringUtils.isNotBlank(tmp[0])) {
                type = tmp[0];
            }
            if (tmp.length > 1 && StringUtils.isNotBlank(tmp[1])) {
                exper = tmp[1];
            }
        }

        // 通过IStandardExpression 解析器 解析表达式获取参数
        List<String> values = new ArrayList<>();
        if (StringUtils.isNotBlank(exper)) {
            values = ThymeleafFacade.evaluateAsStringsWithDelimiter(context, exper, DELIMITER);
        }
        // 标签名
        final String elementCompleteName = tag.getElementCompleteName();

        // 创建模型
        final IModelFactory modelFactory = context.getModelFactory();
        final IModel model = modelFactory.createModel();
        // 添加模型 标签
        model.add(modelFactory.createOpenElementTag(elementCompleteName));

        // 调用具体方法
        switch (type) {
            case GET_LABEL_BY_TYPE:
                // 去除html
                model.add(modelFactory.createText(HtmlEscape.escapeHtml5(getDictName(context, values))));
                break;
            case USER_NAME_BY_TYPE:
                model.add(modelFactory.createText(getNameById(context, values)));
                break;
            case USER_PHONE_BY_TYPE:
                model.add(modelFactory.createText(getPhoneById(context, values)));
                break;
            case USER_ID_BY_TYPE:
                model.add(modelFactory.createText(getUserId(context)));
                break;
            case SELECT_LABEL_BY_TYPE:
//                输出html
                model.add(modelFactory.createText(getLabelSelect(context, values)));
                break;
            case RADIO_LABEL_BY_TYPE:
//                输出html
                model.add(modelFactory.createText(getLabelRadio(context, values)));
                break;
            case CHECKBOX_LABEL_BY_TYPE:
//                输出html
                model.add(modelFactory.createText(getLabelCheckBox(context, values)));
                break;
            default:
                break;
        }

        // 添加模型 标签
        model.add(modelFactory.createCloseElementTag(elementCompleteName));
        // 替换页面标签
        structureHandler.replaceWith(model, false);
    }

    /**
     * 获取对应的标签名
     * @param context
     * @param values
     * @return
     */
    private String getDictName(ITemplateContext context, List<String> values) {
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
        RedisRepository dic = appCtx.getBean(RedisRepository.class);
        String label = dic.getDictLabelByTypeAndValue(values.get(0), values.get(1));
        return label;
    }

    private String getNameById(ITemplateContext context, List<String> values) {
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
        RedisRepository dic = appCtx.getBean(RedisRepository.class);
        User user = dic.getUserById(values.get(0));
        if (user == null) {
            return "用户不存在";
        }
        return user.getNickName();
    }

    private String getPhoneById(ITemplateContext context, List<String> values) {
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
        RedisRepository dic = appCtx.getBean(RedisRepository.class);
        User user = dic.getUserById(values.get(0));
        if (user == null) {
            return "用户不存在";
        }
        return user.getPhone();
    }

    private String getUserId(ITemplateContext context) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return user.getId();
    }

    private String getLabelSelect(ITemplateContext context, List<String> values) {
        StringBuilder sb = new StringBuilder("");
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
        RedisRepository repository = appCtx.getBean(RedisRepository.class);
        List<Dict> dictList = repository.getDictLabelByType(values.get(0));
        dictList.forEach(dict -> {
            if (values.size() > 1) {
                if (dict.getValue().equals(values.get(1))) {
                    sb.append("<option value='" + dict.getValue() + "' selected>" + dict.getLabel() + "</option>");
                    return;
                }
            }
            sb.append("<option value='" + dict.getValue() + "'>" + dict.getLabel() + "</option>");

        });
        return sb.toString();
    }

    private String getLabelRadio(ITemplateContext context, List<String> values) {
        StringBuilder sb = new StringBuilder("");
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
        RedisRepository repository = appCtx.getBean(RedisRepository.class);
        List<Dict> dictList = repository.getDictLabelByType(values.get(0));
        dictList.forEach(dict -> {
            if (values.size() > 2) {
                if (dict.getValue().equals(values.get(2))) {
                    sb.append("<input type=\"radio\" name=\"" + values.get(1) + "\" value=\"" + dict.getValue() + "\" title=\"" + dict.getLabel() + "\" checked>");
                    return;
                }
            }
            sb.append("<input type=\"radio\" name=\"" + values.get(1) + "\" value=\"" + dict.getValue() + "\" title=\"" + dict.getLabel() + "\">");

        });
        return sb.toString();
    }

    private String getLabelCheckBox(ITemplateContext context, List<String> values) {
        StringBuilder sb = new StringBuilder("");
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
        RedisRepository repository = appCtx.getBean(RedisRepository.class);
        List<Dict> dictList = repository.getDictLabelByType(values.get(0));
        dictList.forEach(dict -> {
            if (values.size() > 2) {
                if (dict.getValue().equals(values.get(2))) {
                    sb.append("<input type=\"checkbox\" name=\"" + values.get(1) + "\" value=\"" + dict.getValue() + "\" title=\"" + dict.getLabel() + "\" checked>");
                    return;
                }
            }
            sb.append("<input type=\"checkbox\" name=\"" + values.get(1) + "\" value=\"" + dict.getValue() + "\" title=\"" + dict.getLabel() + "\">");

        });
        return sb.toString();
    }


}
