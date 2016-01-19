package com.rearticle.model;

import hibernate.util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ReArticleDAOHibernate implements ReArticleDAO_interface {

	public static void main(String[] args) {
		ReArticleDAOHibernate dao = new ReArticleDAOHibernate();
	}

	private static final String INSERT_RE_ARTICLE = "Select count(Re_Id) From ReArticleBean where article_id = ?";
	@Override
	public int insertReArticle(ReArticleBean bean) {
		long reId = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			reId = (long) session.createQuery(INSERT_RE_ARTICLE)
					.setInteger(0, bean.getArticleId())
					.uniqueResult();
			bean.setReId((int) (reId++));
			session.save(bean);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return (int) reId;
	}

	@Override
	public int delete(int reId) throws SQLException {

		return 0;
	}

	private static final String UPDATE_RE_ARTICLE = "From ReArticleBean where article_id = ? and re_id = ? and member_id = ? ";
	@Override
	public void updateReArticleHidden(int articleId, int reId, int memberId,
			int hidden) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			ReArticleBean bean = (ReArticleBean) session.createQuery(UPDATE_RE_ARTICLE)
					.setParameter(0, articleId)
					.setParameter(1, reId)
					.setParameter(2, memberId)
					.uniqueResult();
			if (bean != null) {
				bean.setHidden(hidden);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void updateReArticleHidden(int articleId, int reId, int hidden) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			session.beginTransaction();
			ReArticleBean bean = (ReArticleBean) session.createQuery("From ReArticleBean where article_id = ? and re_id = ?")
					.setParameter(0, articleId)
					.setParameter(1, reId)
					.uniqueResult();
			if (bean != null) {
				bean.setHidden(hidden);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void updateReArticle(String reContent, int reId, int articleId,
			int memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ReArticleBean bean = (ReArticleBean) session.createQuery(UPDATE_RE_ARTICLE)
					.setParameter(0, articleId)
					.setParameter(1, reId)
					.setParameter(2, memberId)
					.uniqueResult();
			if (bean != null) {
				bean.setReContent(reContent);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReArticleBean> getAllReArticle(int articleId) {
		List<ReArticleBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session
					.createQuery("From ReArticleBean where article_id = ?")
					.setInteger(0, articleId)
					.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return beans;
	}

	@Override
	public ReArticleBean getLastReArticle(int articleId) {
		ReArticleBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (ReArticleBean) session.createQuery("From ReArticleBean where article_Id = ? order by re_make desc")
					.setParameter(0, articleId)
					.setMaxResults(1)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public ReArticleBean getOneReArticle(int articleId, int reId) {
		ReArticleBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (ReArticleBean) session.createQuery("From ReArticleBean where article_Id = ? and re_id = ?")
					.setParameter(0, articleId)
					.setParameter(1, reId)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

}
