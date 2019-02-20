package com.basic.zyz.common.config;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * @author zyz
 * @date 2019/1/13
 */
public class WorkFocusDialect extends AbstractDialect implements IExpressionObjectDialect {

    private final IExpressionObjectFactory EXPRESSION_OBJECTS_FACTORY = new WorkFocusExpressionFactory();

    public WorkFocusDialect() {
        super(WorkFocusExpressionFactory.STRING_UTILS_EXPRESSION_OBJECT_NAME);
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return this.EXPRESSION_OBJECTS_FACTORY;
    }

}
