package com.point.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="MEMBER")
public class MemberBean implements java.io.Serializable{
	@Id
	private int member_id;
	private String account;
	private byte[]  password;
	private String username;
	private String sex;
	private java.util.Date birth;
	private String email;
	private String phone;
	private String address;
	private java.util.Date logindate;
	private java.sql.Timestamp last_date;
	private String  fromfb;
	private byte[] picture;
	private  int statu;
	private int point;
	private int order_math;
	private String  Nickname;
	public int getStatu() {
		return statu;
	}



	public void setStatu(int statu) {
		this.statu = statu;
	}



	public String getNickName() {
		return Nickname;
	}



	public void setNickName(String nickName) {
		Nickname = nickName;
	}



	public MemberBean(){  //空白建構子 jdbc
		
	}
	
	

	@Override
	public String toString() {
		return " [" + member_id + ", " + account + ", " + point + "]";
	}



	public MemberBean(int member_id, String account, byte[] password, String username, String sex, Date birth,
			String email, String phone, String address, Date logindate, Timestamp last_date, String fromfb,
			byte[] picture, int status, int point, int order_math) {
		super();
		this.member_id = member_id;
		this.account = account;
		this.password = password;
		this.username = username;
		this.sex = sex;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.logindate = logindate;
		this.last_date = last_date;
		this.fromfb = fromfb;
		this.picture = picture;
		this.statu = statu;
		this.point = point;
		this.order_math = order_math;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public java.util.Date getBirth() {
		return birth;
	}

	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public java.util.Date getLogindate() {
		return logindate;
	}

	public void setLogindate(java.util.Date logindate) {
		this.logindate = logindate;
	}

	public java.sql.Timestamp getLast_date() {
		return last_date;
	}

	public void setLast_date(java.sql.Timestamp last_date) {
		this.last_date = last_date;
	}

	public String getFromfb() {
		return fromfb;
	}

	public void setFromfb(String fromfb) {
		this.fromfb = fromfb;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public int getStatus() {
		return statu;
	}

	public void setStatus(int status) {
		this.statu = status;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getOrder_math() {
		return order_math;
	}

	public void setOrder_math(int order_math) {
		this.order_math = order_math;
	}



	
	}







