package com.article.model;

import java.util.List;

import com.rearticle.model.ReArticleBean;

public class ArticleDAOJDBC implements ArticleDAO_interface {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=bakeryDB";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa123456";
	@Override
	public void insertArticle(ArticleBean bean) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int delete(int articleId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void updateArticleHidden(int articleId, int memberId, int hidden) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean updateArticle(int articleClassNo, String articleTitle,
			String content, int articleId, int memberId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void updateReArticleCount(int reId, int articleId, ReArticleBean bean) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateBrowserCount(int articleId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArticleBean getOne(int articleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getRecordCounts() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<ArticleBean> getPageArticles(int startRow, int recordsPerRow) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getRecordCounts(int articleClassNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<ArticleBean> getPageArticlesClass(int articleClassNo,
			int startRow, int recordsPerRow) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getRecordCounts(String articleTitle) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<ArticleBean> getPageArticlesTitle(String articleTitle,
			int startRow, int recordsPerRow) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ArticleClassBean> getArticleClass() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getArticleTitle(int articleId) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
