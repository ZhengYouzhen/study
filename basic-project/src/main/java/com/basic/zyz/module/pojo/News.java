package com.basic.zyz.module.pojo;

import org.hibernate.validator.constraints.Length;

import com.basic.zyz.common.basic.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 资讯管理Entity
 * @author zyzz
 * @version 2018-12-16
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class News extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String photo;		// 图片,不能为空
	private String infoTitle;		// 标题
	private String subTitle;		// 副标题
	private String content;		// 资讯内容

	private String startCreateDate;
	private String endCreateDate;

	private String nickName;	//发布资讯的用户名

	public News() {
		super();
	}

	public News(String id){
		super(id);
	}

	@Length(min=1, max=60, message="图片,不能为空长度不能为空且不能超过60个字符")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=1, max=60, message="标题长度不能为空且不能超过60个字符")
	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	
	@Length(min=0, max=255, message="副标题长度不能超过 255个字符")
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}