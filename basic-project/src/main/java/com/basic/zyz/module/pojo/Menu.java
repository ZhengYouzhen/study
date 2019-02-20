package com.basic.zyz.module.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;

import org.hibernate.validator.constraints.Length;

import com.basic.zyz.common.basic.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 菜单管理Entity
 * @author zyz
 * @version 2018-12-18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	public static final String PARENT_ID = "0";
	private String parentId;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private String sort;		// 排序
	private String href;		// 链接
	private String target;		// 目标
	private String icon;		// 图标
	private String isShow;		// 是否在菜单中显示
	private String permission;		// 权限标识

	private String option;		//接收前台的操作标识

	public Menu() {
		super();
	}

	public Menu(String id){
		super(id);
	}

	@Length(min=1, max=255, message="parentId")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Length(min=1, max=2000, message="所有父级编号长度不能为空且不能超过2000个字符")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="名称长度不能为空且不能超过100个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=2000, message="链接长度不能超过 2000个字符")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Length(min=0, max=20, message="目标长度不能超过 20个字符")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Length(min=0, max=100, message="图标长度不能超过 100个字符")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=1, max=1, message="是否在菜单中显示长度不能为空且不能超过1个字符")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	@Length(min=0, max=200, message="权限标识长度不能超过 200个字符")
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}