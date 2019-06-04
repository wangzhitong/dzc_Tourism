package com.dzcTourism.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "dzc_user")
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User {

	private String id;
	
	private String userName;
	
	private String password;
	
	private String idCard;

	private String gender;
	
	private String phoneNum;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, String userName, String password, String idCard, String gender, String phoneNum) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.idCard = idCard;
		this.gender = gender;
		this.phoneNum = phoneNum;
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

	@Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})"
    		,message="用户名必须是2-5位中文或者6-16位英文和数字的组合")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$"
			,message = "密码由6-21字母和数字组成，不能是纯数字或纯英文")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull(message = "身份证号不能为空")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@NotNull(message = "联系电话不能为空")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
