package com.basic.zyz.common.config;

import com.basic.zyz.common.web.WebUserInfo;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author zyz
 * @date 2019/1/13
 */
public class WorkFocusExpressionFactory implements IExpressionObjectFactory {

    public static final String STRING_UTILS_EXPRESSION_OBJECT_NAME = "getUtils";

    private static final WebUserInfo getUtils = new WebUserInfo();


    public static final Set<String> ALL_EXPRESSION_OBJECT_NAMES;

    static {

        final Set<String> allExpressionObjectNames = new LinkedHashSet<>();
        allExpressionObjectNames.add(STRING_UTILS_EXPRESSION_OBJECT_NAME);
        ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(allExpressionObjectNames);

    }

    public WorkFocusExpressionFactory() {
        super();
    }

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext iExpressionContext, String expressionObjectName) {
        return STRING_UTILS_EXPRESSION_OBJECT_NAME.equals(expressionObjectName) ? getUtils : null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && "getUtils".equals(expressionObjectName);
    }



}
