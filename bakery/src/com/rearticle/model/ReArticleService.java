package com.rearticle.model;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import com.article.model.ArticleDAOHibernate;
import com.article.model.ArticleService;

public class ReArticleService {
	private ReArticleDAO_interface dao;

	public ReArticleService() {
		dao = new ReArticleDAOHibernate();
	}

	public void insertReArticle(int memberId, String reContent, int articleId) {
		ReArticleBean bean = new ReArticleBean();
		bean.setMemberId(memberId);
		bean.setReContent(reContent);
		bean.setArticleId(articleId);
		bean.setReMake(new java.util.Date());
		bean.setHidden(0);
		int reId = dao.insertReArticle(bean);
		ArticleDAOHibernate articleDAO = new ArticleDAOHibernate();
		if (reId != 0) {
			articleDAO.updateReArticleCount(reId, articleId, bean);
		}
	}

	public void updateReArticle(String reContent, int reId, int articleId, int memberId) {
		dao.updateReArticle(reContent, reId, articleId, memberId);
	}
	
	public void updateReArticleHidden(int articleId, int reId, int memberId, int hidden){
		dao.updateReArticleHidden(articleId, reId, memberId, hidden);
	}
	
	public void updateReArticleHidden(int Id, int hidden){
		dao.updateReArticleHidden(Id, hidden);
	}

	public ReArticleBean getOneReArticle(int articleId, int reId) {
		return dao.getOneReArticle(articleId, reId);
	}

	public List<ReArticleBean> getAllReArticle(int articleId) {
		List<ReArticleBean> beans = dao.getAllReArticle(articleId);
		for (ReArticleBean bean : beans) {
			bean.setAccount(bean.getMember().getAccount());
			bean.setNickName(bean.getMember().getNickname());
			bean.setPicture(Base64.encodeBase64String(bean.getMember().getPicture()));
			if(bean.getHidden() == 1){
				bean.setReContent("<span style='color:#999999'>此文章已被發文者刪除</span>");
			}else if(bean.getHidden() == 2){
				bean.setReContent("<span style='color:#999999'>此文章已被管理員刪除</span>");
			}
			bean.setReArticleMakeDate(new ArticleService().convertDate(bean.getReMake()));
		}
		return beans;
	}
}
