package com.articlecollection.model;

public class ArticleCollectionBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int memberId;
	private int articleId;
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

}
