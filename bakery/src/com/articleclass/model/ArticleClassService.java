package com.articleclass.model;

import java.util.List;

public class ArticleClassService {
	private ArticleClassDAO_interface dao;

	public ArticleClassService() {
		dao = new ArticleClassDAOHibernate();
	}

	public void insertArticleClass(String articleClassName) {
		ArticleClassBean bean = new ArticleClassBean();
		bean.setArticleClassName(articleClassName);
		dao.insertArticleClass(bean);
	}
	
	public void updateArticleClass(int articleClassNo , String articleClassName) {
		ArticleClassBean bean = new ArticleClassBean();
		bean.setArticleClassNo(articleClassNo);
		bean.setArticleClassName(articleClassName);
		dao.insertArticleClass(bean);
	}

	public void deleteArticleClass(int articleClassNo) {
		dao.deleteArticleClass(articleClassNo);
	}
	
	// 查詢文章種類
	public List<ArticleClassBean> getArticleClass() {
		return dao.getArticleClass();
	}
}
