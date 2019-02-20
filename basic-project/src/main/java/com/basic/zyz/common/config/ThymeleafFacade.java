package com.basic.zyz.common.config;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressionParser;
import org.thymeleaf.util.EvaluationUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.thymeleaf.util.StringUtils.trim;
import static org.thymeleaf.util.Validate.notEmpty;
import static org.thymeleaf.util.Validate.notNull;

public final class ThymeleafFacade {

    private ThymeleafFacade() {
        throw new UnsupportedOperationException();
    }

    static String getRawValue(final IProcessableElementTag element, final AttributeName attributeName) {
        notNull(element, "element must not be null");
        notNull(attributeName, "attributeName must not be empty");

        final String rawValue = trim(element.getAttributeValue(attributeName));
        notEmpty(rawValue, "value of '" + attributeName + "' must not be empty");

        return rawValue;
    }

    static String getRawValue(final IProcessableElementTag element, final String attributeName) {
        notNull(element, "element must not be null");
        notEmpty(attributeName, "attributeName must not be empty");

        final String rawValue = trim(element.getAttributeValue(attributeName));
        notEmpty(rawValue, "value of '" + attributeName + "' must not be empty");

        return rawValue;
    }

    static Object evaluateExpression(ITemplateContext arguments, String expression)
            throws TemplateProcessingException {
        notNull(arguments, "arguments must not be null");
        notEmpty(expression, "expression must not be empty");

        final IStandardExpressionParser parser = new StandardExpressionParser();

        final IStandardExpression evaluableExpression = parser.parseExpression(arguments, expression);

        return evaluableExpression.execute(arguments);
    }

    static List<Object> evaluateAsIterable(ITemplateContext arguments, String rawValue)
            throws TemplateProcessingException {
        notNull(arguments, "arguments must not be null");
        notEmpty(rawValue, "rawValue must not be empty");

        // 多个参数
        String[] raws = rawValue.split(",");

        List<Object> back = new ArrayList<Object>();
        for (String tmp : raws) {
            final Object evaluatedExpression = evaluateExpression(arguments, tmp);
            List<Object> tmpList = EvaluationUtils.evaluateAsList(evaluatedExpression);
            back.addAll(tmpList);
        }

        return back;
    }

    static List<Object> evaluateAsIterableOrRawValue(ITemplateContext arguments, String rawValue) {
        notNull(arguments, "arguments must not be null");
        notEmpty(rawValue, "rawValue must not be empty");

        final List<Object> result = new ArrayList<Object>();
        try {
            result.addAll(evaluateAsIterable(arguments, rawValue));
        } catch (TemplateProcessingException ex) {
            result.add(rawValue);
        }

        return unmodifiableList(result);
    }

    static List<String> evaluateAsStringsWithDelimiter(ITemplateContext arguments, String rawValue,
                                                       String delimiter) {
        notNull(arguments, "arguments must not be null");
        notEmpty(rawValue, "rawValue must not be empty");
        notEmpty(delimiter, "delimiter must not be empty");

        final List<String> result = new ArrayList<>();
        final List<Object> iterates = evaluateAsIterableOrRawValue(arguments, rawValue);

        for (Object o : iterates) {
            result.addAll(asList(StringUtils.split(o, delimiter)));
        }

        return unmodifiableList(result);
    }
}
