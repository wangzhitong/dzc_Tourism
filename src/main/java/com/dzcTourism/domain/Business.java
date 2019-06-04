package com.dzcTourism.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 	商家信息
 * @author LU
 *
 */
@Table(name="dzc_business")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Business {

	private String id;
	
	private String businessName;//商家名称
	
	private Date createTime;//

	private String businessInfo;//商家介绍
	
	private String content;//内容
	
	private String businessType; //商家分类
	
	private List<FineFood> foodInfo;//美食信息

	public Business() {
		super();

	}

	public Business(String id, String businessName, Date createTime, String businessInfo, String content,
			String businessType) {
		super();
		this.id = id;
		this.businessName = businessName;
		this.createTime = createTime;
		this.businessInfo = businessInfo;
		this.content = content;
		this.businessType = businessType;
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

	@NotBlank(message = "名称不能为空")
	@NotNull(message = "名称不能为空")
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}

//	@NotNull(message = "内容不能为空")
//	@Column(length = 20000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@JoinColumn(name = "business_id")
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<FineFood> getFoodInfo() {
		return foodInfo;
	}

	public void setFoodInfo(List<FineFood> foodInfo) {
		this.foodInfo = foodInfo;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", businessName=" + businessName + ", createTime=" + createTime + ", businessInfo=" + businessInfo
				+ ", content=" + content + "]";
	}

	
}
