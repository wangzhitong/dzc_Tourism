package com.dzcTourism.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商品种类管理
 * @author wangzhit
 *
 */
@Table(name = "dzc_commodityCategory")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CommodityCategory {

	private String id;
	
	private Date createTime;
	
	private String categoryName;

	private String remark;

	public CommodityCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommodityCategory(String categoryName, String remark) {
		super();
		this.categoryName = categoryName;
		this.remark = remark;
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
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(length = 1000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
