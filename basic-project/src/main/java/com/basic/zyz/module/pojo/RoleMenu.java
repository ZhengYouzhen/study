package com.basic.zyz.module.pojo;

import org.hibernate.validator.constraints.Length;

import com.basic.zyz.common.basic.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 角色菜单Entity
 * @author zyz
 * @version 2018-12-18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleMenu extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String roleId;		// 角色编号
	private String menuId;		// 菜单编号

	public RoleMenu() {
		super();
	}

	public RoleMenu(String roleId){
		super(roleId);
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Length(min=1, max=64, message="菜单编号长度不能为空且不能超过64个字符")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}