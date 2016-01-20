package com.article.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.articleclass.model.ArticleClassBean;
import com.member.model.MemberBean;

@Entity
@Table(name = "article")
public class ArticleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MemberBean member;

	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}

	@ManyToOne
	@JoinColumn(name = "article_class_no", referencedColumnName = "article_class_no", insertable = false, updatable = false)
	private ArticleClassBean articleClass;

	public ArticleClassBean getArticleClass() {
		return articleClass;
	}

	public void setArticleClass(ArticleClassBean articleClass) {
		this.articleClass = articleClass;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_Id")
	private int articleId;

	@Column(name = "member_Id")
	private int memberId;

	@Column(name = "article_Class_No")
	private int articleClassNo;

	@Column(name = "article_Title")
	private String articleTitle;

	@Column(name = "content")
	private String content;

	@Column(name = "re_Article_Count")
	private Integer reArticleCount;

	@Column(name = "browser_Count")
	private Integer browserCount;

	@Column(name = "article_Make")
	private java.util.Date articleMake;

	@Column(name = "re_Article_Date")
	private java.util.Date reArticleMake;

	@Column(name = "hidden")
	private Integer hidden;

	@Transient
	private String articleClassName;

	public String getArticleClassName() {
		return articleClassName;
	}

	public void setArticleClassName(String articleClassName) {
		this.articleClassName = articleClassName;
	}

	@Transient
	private byte[] pictureTemp;

	@Transient
	private String picture;

	@Transient
	private String author;

	@Transient
	private String nickName;

	@Transient
	private String articleMakeDate;

	@Transient
	private String reAuthor;

	@Transient
	private String reArticleMakeDate;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getArticleClassNo() {
		return articleClassNo;
	}

	public void setArticleClassNo(int articleClassNo) {
		this.articleClassNo = articleClassNo;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReArticleCount() {
		return reArticleCount;
	}

	public void setReArticleCount(Integer reArticleCount) {
		this.reArticleCount = reArticleCount;
	}

	public Integer getBrowserCount() {
		return browserCount;
	}

	public void setBrowserCount(Integer browserCount) {
		this.browserCount = browserCount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public java.util.Date getArticleMake() {
		return articleMake;
	}

	public void setArticleMake(java.util.Date articleMake) {
		this.articleMake = articleMake;
	}

	public String getArticleMakeDate() {
		return articleMakeDate;
	}

	public void setArticleMakeDate(String articleMakeDate) {
		this.articleMakeDate = articleMakeDate;
	}

	public String getReAuthor() {
		return reAuthor;
	}

	public void setReAuthor(String reAuthor) {
		this.reAuthor = reAuthor;
	}

	public java.util.Date getReArticleMake() {
		return reArticleMake;
	}

	public void setReArticleMake(java.util.Date reArticleMake) {
		this.reArticleMake = reArticleMake;
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

}
