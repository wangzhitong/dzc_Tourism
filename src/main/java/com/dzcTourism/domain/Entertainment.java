package com.dzcTourism.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 *	游玩娱乐
 * @author wangzhitong
 *
 */

@Table(name="dzc_entertainment")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Entertainment {

	//主键
	private String id;
	
	//标题
	private String title;
	
	//副标题
	private String subheading;
	
	//创建时间
	private Date createTime;

	//富文本编辑内容主体
	private String content;
	

	public Entertainment() {
		super();

	}

	public Entertainment(String id, String title, String subheading, Date createTime, String content) {
		super();
		this.id = id;
		this.title = title;
		this.subheading = subheading;
		this.createTime = createTime;
		this.content = content;
	}

	@GeneratedValue(generator = "jpa-uuid")
	@Column(length = 32)
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotBlank(message = "标题不能为空")
	@NotNull(message = "标题不能为空")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubheading() {
		return subheading;
	}

	public void setSubheading(String subheading) {
		this.subheading = subheading;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@NotNull(message = "内容不能为空")
	@Column(length = 20000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", subheading=" + subheading + ", createTime=" + createTime
				+ ", content=" + content + "]";
	}
	
	
	
}
