package com.dzcTourism.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 美食信息
 * @author wangzhitong
 *
 */
@Table(name = "dzc_finefood")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class FineFood {

	
	private String id;
	
	private String foodName;
	
	private String picture1;

	private String picture2;
	
	private String picture3;
	
	private String picture4;
	
	private String picture5;
	
	private String description;
	
	private String business_id;
	
	private String category;
	

	public FineFood() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FineFood(String id, String foodName, String picture1, String picture2, String picture3, String picture4,
			String picture5, String description) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.picture3 = picture3;
		this.picture4 = picture4;
		this.picture5 = picture5;
		this.description = description;
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
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	@Column(length = 500)
	public String getPicture1() {
		return picture1;
	}


	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	@Column(length = 500)
	public String getPicture2() {
		return picture2;
	}


	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}

	@Column(length = 500)
	public String getPicture3() {
		return picture3;
	}


	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}

	@Column(length = 500)
	public String getPicture4() {
		return picture4;
	}


	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}

	@Column(length = 500)
	public String getPicture5() {
		return picture5;
	}


	public void setPicture5(String picture5) {
		this.picture5 = picture5;
	}

	@Column(length =1000)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

//	@JoinColumn(name = "business_id")
//	@ManyToOne
	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	
//	@JoinColumn(name = "category_id",unique = true)
//	@OneToOne
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "FineFood [id=" + id + ", foodName=" + foodName + ", picture1=" + picture1 + ", description="
				+ description + ", business_id=" + business_id + "]";
	}
	
	

}
