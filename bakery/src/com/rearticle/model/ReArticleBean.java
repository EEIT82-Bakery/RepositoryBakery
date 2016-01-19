package com.rearticle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.article.model.ArticleBean;
import com.member.model.MemberBean;

@Entity
@Table(name = "Re_Article")
public class ReArticleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "article_id", referencedColumnName = "article_id", insertable = false, updatable = false)
	private ArticleBean article;
	
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id" , insertable = false , updatable = false)
	private MemberBean member;
	
	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}

	
	
	@Override
	public String toString() {
		return "ReArticleBean [id=" + id + ", reId=" + reId + ", memberId="
				+ memberId + ", reContent=" + reContent + ", reMake=" + reMake
				+ ", hidden=" + hidden + ", articleId=" + articleId + "]";
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "re_id")
	private Integer reId;

	@Column(name = "member_id")
	private int memberId;

	@Column(name = "re_content")
	private String reContent;

	@Column(name = "re_make")
	private java.util.Date reMake;

	@Column(name = "hidden")
	private Integer hidden;

	@Column(name = "article_Id")
	private Integer articleId;

	@Transient
	private String account;

	@Transient
	private String nickName;

	@Transient
	private byte[] pictureTemp;

	@Transient
	private String picture;

	@Transient
	private String articleTitle;

	@Transient
	private String reArticleMakeDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getReId() {
		return reId;
	}

	public void setReId(Integer reId) {
		this.reId = reId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public byte[] getPictureTemp() {
		return pictureTemp;
	}

	public void setPictureTemp(byte[] pictureTemp) {
		this.pictureTemp = pictureTemp;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public java.util.Date getReMake() {
		return reMake;
	}

	public void setReMake(java.util.Date reMake) {
		this.reMake = reMake;
	}

	public String getReArticleMakeDate() {
		return reArticleMakeDate;
	}

	public void setReArticleMakeDate(String reArticleMakeDate) {
		this.reArticleMakeDate = reArticleMakeDate;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

}
