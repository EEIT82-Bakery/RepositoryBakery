package com.articlecollection.model;

public class ArticleCollectionService {
	
	private ArticleCollectionDAOJNDI dao;
	public ArticleCollectionService(){
		dao = new ArticleCollectionDAOJNDI();
	}
	
	public void addArticleCollection(int memberId, int articleId){
		dao.addCollection(memberId, articleId);
	}
	
	public boolean getHaveCollection(int memberId, int articleId){
		return dao.getHaveCollection(memberId, articleId);
	}
}
