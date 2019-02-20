package com.basic.zyz.common.config;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.LinkedHashSet;
import java.util.Set;

public class CustomDialect extends AbstractProcessorDialect {
    
	private static final String DIALECT_NAME = "addtion";// 定义方言名称
	private static final String DIALECT_PREFIX = "adds";// 定义方言名称
	

	public CustomDialect() {
		// 我们将设置此方言与“方言处理器”优先级相同
		// 标准方言, 以便处理器执行交错。
		super(DIALECT_NAME, DIALECT_PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

    @Override  
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        return createStandardProcessorsSet(dialectPrefix);  
    }
    
    private Set<IProcessor> createStandardProcessorsSet(String dialectPrefix) {
        LinkedHashSet<IProcessor> processors = new LinkedHashSet<>();
        processors.add(new CustomerTagProcessor(dialectPrefix));  
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    } 

}
