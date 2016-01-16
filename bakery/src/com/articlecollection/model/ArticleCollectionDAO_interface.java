package com.articlecollection.model;

public interface ArticleCollectionDAO_interface {
	public abstract void addCollection(int memberId , int articleId);
	public abstract boolean getHaveCollection(int memberId , int articleId);
}
