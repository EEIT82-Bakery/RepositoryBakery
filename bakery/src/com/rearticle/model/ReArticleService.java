package com.rearticle.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import com.article.model.ArticleDAOJNDI;
import com.article.model.ArticleService;

public class ReArticleService {
	private ReArticleDAO_interface dao;

	public ReArticleService() {
		dao = new ReArticleDAOJNDI();
	}

	public void insertReArticle(int memberId, String reContent, int articleId) {
		int reId = dao.insertReArticle(memberId, reContent, articleId);
		ReArticleBean bean = dao.getLastReArticle(articleId);
		ArticleDAOJNDI articleDAO = new ArticleDAOJNDI();
		if (reId != 0) {
			articleDAO.updateReArticleCount(reId, articleId, bean);
		}
	}

	public boolean updateReArticle(String reContent, int reId, int articleId, int memberId) {
		dao.updateReArticle(reContent, reId, articleId, memberId);
		return true;
	}
	
	public void updateReArticleHidden(int articleId, int reId, int memberId, int hidden){
		dao.updateReArticleHidden(articleId, reId, memberId, hidden);
	}

	public ReArticleBean getOneReArticle(int articleId, int reId) {
		return dao.getOneReArticle(articleId, reId);
	}

	public List<ReArticleBean> getAllReArticle(int articleId) {
		List<ReArticleBean> beans = new ArrayList<>();
		List<ReArticleBean> temp = dao.getAllReArticle(articleId);
		for (ReArticleBean bean : temp) {
			bean.setPicture(Base64.encodeBase64String(bean.getPictureTemp()));
			if(bean.getHidden() == 0){
				bean.setReContent(bean.getReContent());	
			}else{
				bean.setReContent("<span style='color:#999999'>此文章已被刪除</span>");
			}
			bean.setReArticleMakeDate(new ArticleService().convertDate(bean.getReMake()));
			beans.add(bean);
		}
		return beans;
	}
}
