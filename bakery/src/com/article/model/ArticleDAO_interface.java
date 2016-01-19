package com.article.model;

import java.util.List;

import com.articleclass.model.ArticleClassBean;
import com.rearticle.model.ReArticleBean;

public interface ArticleDAO_interface {
	public abstract void insertArticle(ArticleBean bean);
	public abstract int delete(int articleId);
	public abstract void updateArticleHidden(int articleId , int memberId , int hidden);
	public abstract void updateArticleHidden(int articleId , int hidden);
	public abstract void updateArticle(int articleClassNo , String articleTitle , String content, int articleId, int memberId);
	public abstract void updateReArticleCount(int reId, int articleId, ReArticleBean bean);
	public abstract void updateBrowserCount(int articleId);
	public abstract int getBrowserCount(int articleId);
	public abstract ArticleBean getOne(int articleId);
	public abstract int getRecordCounts();
	public abstract List<ArticleBean> getPageArticles(int startRow, int recordsPerRow);
	public abstract int getRecordCounts(int articleClassNo);
	public abstract List<ArticleBean> getPageArticlesClass(int articleClassNo, int startRow, int recordsPerRow);
	public abstract int getRecordCounts(String articleTitle);
	public abstract List<ArticleBean> getPageArticlesTitle(String articleTitle , int startRow, int recordsPerRow);
	public abstract String getArticleTitle(int articleId);
	
}
