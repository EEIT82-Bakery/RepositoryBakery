package com.member.model;

import hibernate.util.HibernateUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.membergrade.model.MemberGradeBean;



@Entity
@Table(name = "Member")
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@JoinColumn(
			name="STATU",
			referencedColumnName="STATU",
			insertable=false,updatable=false
			)
	private MemberGradeBean membergrade;
	
	public MemberGradeBean getMeber() {
		return membergrade;
	}
	public void setMeber(MemberGradeBean membergrade) {
		this.membergrade = membergrade;
	}

	@Id
	@Column(name="Member_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int member_id;
	@Column(name="Account")
	private String account;
	@Column(name="Password")
	private byte[] password;
	@Column(name="Username")
	private String username;
	@Column(name="Sex")
	private String sex;
	@Column(name="Birth")
	private java.util.Date birth;
	@Column(name="Email")
	private String email;
	@Column(name="Phone")
	private String phone;
	@Column(name="Address")
	private String address;
	@Column(name="Logindate")
	private java.util.Date logindate;
	@Column(name="Last_date")
	private java.sql.Timestamp last_date;
	@Column(name="Fromfb")
	private String fromfb;
	@Column(name="Picture")
	private byte[] picture;
	@Column(name="Statu")
	private int status;
	@Column(name="Point")
	private int point;
	@Column(name="Order_math")
	private int order_math;
	@Column(name="NickName")
	private String nickname;
	
	@Transient
	private String mpicture;
	@Transient
	private String permname;
	@Transient
	private  int  articleid;
	@Column(name="Kanban")
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

	
	
	
	

	public static  void main(String[] args){
		Session session = null;
		MemberBean bean = null;
		try{
			session  = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from Member where account=:acconut");
			query.setParameter("account", 1);
			bean = (MemberBean) query.uniqueResult();
			System.out.println(bean);
		
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
	
	}
		
	}
	
	

