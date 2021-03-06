package com.articleclass.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.article.model.ArticleBean;

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
