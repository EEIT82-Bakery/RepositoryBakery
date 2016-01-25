package com.articleclass.model;

import java.util.List;

public interface ArticleClassDAO_interface {
	public abstract void insertArticleClass(ArticleClassBean bean);
	public abstract void deleteArticleClass(int articleClassNo);
	public abstract void updateArticleClass(ArticleClassBean bean);
	public abstract List<ArticleClassBean> getArticleClass();

}
