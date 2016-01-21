package com.articlereport.model;

import java.util.List;

import hibernate.util.HibernateUtil;

import org.hibernate.Session;


public class ArticleReportDAOHibernate implements ArticleReportDAO_interface{
	
	public static void main(String[] args){
		ArticleReportDAOHibernate dao = new ArticleReportDAOHibernate();
	}

	@Override
	public void insertArticleReport(ArticleReportBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(bean);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void deleteArticleReport(int articleId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.createSQLQuery("delete Form Article_Report where article_Id = ?")
			.setInteger(0, articleId)
			.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void updateArticleReportStatus(int articleId, int reportStatus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.createSQLQuery("update Article_Report set report_Status  = ? where article_Id = ?")
			.setParameter(0, reportStatus)
			.setParameter(1, articleId)
			.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public ArticleReportBean getOneArticleReport(int articleReportId) {
		ArticleReportBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (ArticleReportBean) session.get(ArticleReportBean.class, articleReportId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public List<ArticleReportBean> getAllArticleReport() {
		List<ArticleReportBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ArticleReportBean").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return beans;
	}

	@Override
	public List<ArticleReportBean> getAllArticleReport(int reportStatus) {
		List<ArticleReportBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ArticleReportBean where Report_Status = ?")
					.setParameter(0, reportStatus)
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return beans;
	}

	@Override
	public boolean isReport(int memberId, int articleId) {
		ArticleReportBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (ArticleReportBean) session.createQuery("From ArticleReportBean where member_Id = ? and article_Id = ?")
					.setParameter(0, memberId)
					.setParameter(1, articleId)
					.uniqueResult();
			session.getTransaction().commit();
			if(bean != null){
				return true;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}
}
