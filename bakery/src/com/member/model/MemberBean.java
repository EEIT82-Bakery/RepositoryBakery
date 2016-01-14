package com.member.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int member_id;
	private String account;
	private byte[] password;
	private String username;
	private String sex;
	private java.util.Date birth;
	private String email;
	private String phone;
	private String address;
	private java.util.Date logindate;
	private java.sql.Timestamp last_date;
	private String fromfb;
	private byte[] picture;
	private int status;
	private int point;
	private int order_math;
	private String nickname;
	private String mpicture;
	private String permname;
	private  int  articleid;
	private String kanban;
	
	
	public MemberBean() {

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

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static java.util.Date convertDate(String temp) {
		java.util.Date result = new java.util.Date();
		try {
			result = sdf.parse(temp);
		} catch (ParseException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
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
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}	
	public String getMpicture() {
		return mpicture;
	}
	public void setMpicture(String mpicture) {
		this.mpicture = mpicture;
	}
	public String getPermname() {
		return permname;
	}

	public void setPermname(String permname) {
		this.permname = permname;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public String getKanban() {
		return kanban;
	}
	public void setKanban(String kanban) {
		this.kanban = kanban;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
