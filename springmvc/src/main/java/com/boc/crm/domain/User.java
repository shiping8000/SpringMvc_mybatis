package com.boc.crm.domain;

import com.boc.crm.base.model.BaseModel;

/**
 * 
 * <br>
 * <b>功能：</b>UserEntity<br>
 */
public class User extends BaseModel {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;//   id 主键	private java.lang.String userName;//   姓名	private java.lang.String password;//   	private java.lang.Integer age;//   年龄	private java.lang.String sex;//   性别	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public java.lang.String getUserName() {	    return this.userName;	}	public void setUserName(java.lang.String userName) {	    this.userName=userName;	}	public java.lang.String getPassword() {	    return this.password;	}	public void setPassword(java.lang.String password) {	    this.password=password;	}	public java.lang.Integer getAge() {	    return this.age;	}	public void setAge(java.lang.Integer age) {	    this.age=age;	}	public java.lang.String getSex() {	    return this.sex;	}	public void setSex(java.lang.String sex) {	    this.sex=sex;	}
}

