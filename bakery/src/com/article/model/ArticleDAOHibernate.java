package com.article.model;

import hibernate.util.HibernateUtil;

import java.util.List;

import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.rearticle.model.ReArticleBean;

public class ArticleDAOHibernate implements ArticleDAO_interface{

	
	public static void main(String[] args){
		ArticleBean bean = new ArticleBean();
		bean.setArticleClassNo(1);
		bean.setArticleMake(new java.util.Date());
		bean.setArticleTitle("123");
		bean.setBrowserCount(0);
		bean.setHidden(0);
		bean.setMemberId(1);
		bean.setReArticleCount(0);
		bean.setReArticleMake(new java.util.Date());
		bean.setContent("12315646");
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			int articleId = (Integer) session.save(bean);
			System.out.println(articleId);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	
	
	@Override
	public int insertArticle(ArticleBean bean) {
		int articleId = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			articleId = (Integer) session.save(bean);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
		return articleId;
	}
	@Override
	public int delete(int articleId) {

		return 0;
	}
	@Override
	public void updateArticleHidden(int articleId, int memberId, int hidden) {

		
	}
	@Override
	public boolean updateArticle(int articleClassNo, String articleTitle,
			String content, int articleId, int memberId) {

		return false;
	}
	@Override
	public void updateReArticleCount(int reId, int articleId, ReArticleBean bean) {

		
	}
	@Override
	public int getBrowserCount(int articleId) {

		return 0;
	}
	@Override
	public void updateBrowserCount(int articleId, int browserCount) {

		
	}
	@Override
	public ArticleBean getOne(int articleId) {

		return null;
	}
	@Override
	public int getRecordCounts() {
		long count;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			count = (Long) session.createQuery("Select count(*) From ArticleBean").uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw e;
		}
		return (int) count;
	}
	@Override
	public List<ArticleBean> getPageArticles(int startRow, int recordsPerRow) {
		List<ArticleBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ArticleBean order by re_article_date desc").setFirstResult(startRow).setMaxResults(recordsPerRow).list();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}
	@Override
	public int getRecordCounts(int articleClassNo) {
		long count;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			count = (Long) session.createQuery("Select count(*) From ArticleBean where article_Class_no = ?").setInteger(0, articleClassNo).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw e;
		}
		return (int) count;
	}
	@Override
	public List<ArticleBean> getPageArticlesClass(int articleClassNo,
			int startRow, int recordsPerRow) {
		List<ArticleBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ArticleBean where article_class_no = ? order by re_article_date desc").setInteger(0, articleClassNo).setFirstResult(startRow).setMaxResults(recordsPerRow).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}
	@Override
	public int getRecordCounts(String articleTitle) {
		long count;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			count = (Long) session.createQuery("Select count(*) From ArticleBean where article_title like ?").setString(0,"%" + articleTitle + "%").uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw e;
		}
		return (int) count;
	}
	@Override
	public List<ArticleBean> getPageArticlesTitle(String articleTitle,
			int startRow, int recordsPerRow) {
		List<ArticleBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ArticleBean where article_Title like ? order by re_article_date desc").setString(0, "%"+articleTitle+"%").setFirstResult(startRow).setMaxResults(recordsPerRow).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}
	@Override
	public List<ArticleClassBean> getArticleClass() {
		List<ArticleClassBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ArticleClassBean").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}
	@Override
	public String getArticleTitle(int articleId) {

		return null;
	}
}
