package com.article.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Article_Class")
public class ArticleClassBean {

	@OneToMany(
		mappedBy="articleClass",
		cascade={
				CascadeType.REMOVE
		}
	)
	
	private Set<ArticleBean> articles;
	
	@Override
	public String toString() {
		return articleClassName;
	}

	public Set<ArticleBean> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleBean> articles) {
		this.articles = articles;
	}

	@Id
	@Column(name = "article_Class_No")
	private int articleClassNo;

	@Column(name = "article_Class_Name")
	private String articleClassName;

	public int getArticleClassNo() {
		return articleClassNo;
	}

	public void setArticleClassNo(int articleClassNo) {
		this.articleClassNo = articleClassNo;
	}

	public String getArticleClassName() {
		return articleClassName;
	}

	public void setArticleClassName(String articleClassName) {
		this.articleClassName = articleClassName;
	}

}
