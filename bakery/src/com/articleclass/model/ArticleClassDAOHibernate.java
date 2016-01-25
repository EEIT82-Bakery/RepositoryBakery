package com.articleclass.model;

import hibernate.util.HibernateUtil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.articleclass.model.ArticleClassBean;
import com.rearticle.model.ReArticleBean;

public class ArticleClassDAOHibernate implements ArticleClassDAO_interface{
	
	public static void main(String[] args){
		ArticleClassDAOHibernate dao = new ArticleClassDAOHibernate();
	}
	
	@Override
	public void insertArticleClass(ArticleClassBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(bean);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void deleteArticleClass(int articleClassNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleClassBean bean = (ArticleClassBean) session.get(ArticleClassBean.class, articleClassNo);
			if(bean != null){
				session.delete(bean);	
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void updateArticleClass(ArticleClassBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(bean);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
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

	
	
}
