package com.basic.zyz.module.pojo;

import org.hibernate.validator.constraints.Length;

import com.basic.zyz.common.basic.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 角色管理Entity
 * @author zyz
 * @version 2018-12-18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 角色名称
	private String enname;		// 英文名称
	private String roleType;		// 角色类型
	private String dataScope;		// 数据范围
	private String isSys;		// 是否系统数据
	private String useable;		// 是否可用

	private String startCreateDate;
	private String endCreateDate;


	public Role() {
		super();
	}

	public Role(String id){
		super(id);
	}

	@Length(min=1, max=100, message="角色名称长度不能为空且不能超过100个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="英文名称长度不能超过 255个字符")
	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	
	@Length(min=0, max=255, message="角色类型长度不能超过 255个字符")
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	@Length(min=0, max=1, message="数据范围长度不能超过 1个字符")
	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	
	@Length(min=0, max=64, message="是否系统数据长度不能超过 64个字符")
	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	
	@Length(min=0, max=64, message="是否可用长度不能超过 64个字符")
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(String startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}