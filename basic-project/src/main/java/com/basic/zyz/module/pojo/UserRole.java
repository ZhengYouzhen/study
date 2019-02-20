package com.basic.zyz.module.pojo;

import org.hibernate.validator.constraints.Length;

import com.basic.zyz.common.basic.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 用户角色Entity
 * @author zyz
 * @version 2018-12-18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRole extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String user;		// 用户编号
	private String roleId;		// 角色编号

	public UserRole() {
		super();
	}

	@Length(min=1, max=64, message="用户编号长度不能为空且不能超过64个字符")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=1, max=64, message="角色编号长度不能为空且不能超过64个字符")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}