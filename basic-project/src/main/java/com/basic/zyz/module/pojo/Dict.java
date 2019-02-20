package com.basic.zyz.module.pojo;

import org.hibernate.validator.constraints.Length;

import com.basic.zyz.common.basic.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 字典管理Entity
 * @author zyz
 * @version 2019-01-07
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dict extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String value;		// 数据值
	private String label;		// 标签名
	private String type;		// 类型
	private String description;		// 描述
	private String sort;		// 排序（升序）
	
	public Dict() {
		super();
	}

	public Dict(String id){
		super(id);
	}

	@Length(min=1, max=50, message="数据值长度不能为空且不能超过50个字符")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=1, max=100, message="标签名长度不能为空且不能超过100个字符")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Length(min=1, max=100, message="类型长度不能为空且不能超过100个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=100, message="描述长度不能为空且不能超过100个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=10, message="排序（升序）长度不能为空且不能超过10个字符")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}