package com.rearticlereport.model;

import hibernate.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;


public class ReArticleReportDAOHibernate implements ReArticleReportDAO_interface{
	
	public static void main(String[] args){
		ReArticleReportDAOHibernate dao = new ReArticleReportDAOHibernate();
	}

	@Override
	public void insertReArticleReport(ReArticleReportBean bean) {
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
	public void deleteReArticleReport(int articleId, int reId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery("delete Form ReArticleReportBean where article_Id = ?")
			.setInteger(0, articleId)
			.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}



	@Override
	public void updateReArticleReportStatus(int articleId, int reId,
			int reportStatus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery("update ReArticleReportBean set report_Status  = ? where article_Id = ? and re_Id = ?")
			.setParameter(0, reportStatus)
			.setParameter(1, articleId)
			.setParameter(2, reId)
			.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}



	@Override
	public ReArticleReportBean getOneReArticleReport(int reReportId) {
		ReArticleReportBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (ReArticleReportBean) session.get(ReArticleReportBean.class, reReportId);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}



	@Override
	public boolean isReReport(int memberId, int articleId, int reId) {
		ReArticleReportBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean =  (ReArticleReportBean) session.createQuery("From ReArticleReportBean where member_Id = ? and article_Id = ? and re_Id = ?")
					.setParameter(0, memberId)
					.setParameter(1, articleId)
					.setParameter(2, reId)
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



	@Override
	public List<ReArticleReportBean> getAllReArticleReport() {
		List<ReArticleReportBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ReArticleReportBean").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return beans;
	}



	@Override
	public List<ReArticleReportBean> getAllReArticleReport(int reportStatus) {
		List<ReArticleReportBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session.createQuery("From ReArticleReportBean where Report_Status = ?")
					.setParameter(0, reportStatus)
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return beans;
	}
}
