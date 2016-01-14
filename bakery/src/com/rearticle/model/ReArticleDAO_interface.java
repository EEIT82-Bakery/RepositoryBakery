package com.rearticle.model;

import java.sql.SQLException;
import java.util.List;

public interface ReArticleDAO_interface {
	public abstract int insertReArticle(int memberId, String reContent,int articleId);
	public abstract int delete(int reId) throws SQLException;
	public abstract void updateReArticleHidden(int articleId , int reId , int memberId , int hidden);
	public abstract boolean updateReArticle(String reContent, int reId, int articleId, int memberId);
	public abstract List<ReArticleBean> getAllReArticle(int articleId);
	public abstract ReArticleBean getLastReArticle(int articleId);
	public abstract ReArticleBean getOneReArticle(int articleId , int reId);
}
