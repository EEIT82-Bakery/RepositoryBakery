package com.article.model;

import hibernate.util.HibernateUtil;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rearticle.model.ReArticleBean;

public class ArticleDAOHibernate implements ArticleDAO_interface {

	@Override
	public void insertArticle(ArticleBean bean) {
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

	private final static String UPDATE_ARTICLE = "From ArticleBean where article_Id = ? and member_Id= ?";

	@Override
	public void updateArticle(int articleClassNo, String articleTitle,
			String content, int articleId, int memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleBean bean = (ArticleBean) session
					.createQuery(UPDATE_ARTICLE).setParameter(0, articleId)
					.setParameter(1, memberId).uniqueResult();
			if (bean != null) {
				bean.setArticleClassNo(articleClassNo);
				bean.setArticleTitle(articleTitle);
				bean.setContent(content);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void updateArticleHidden(int articleId, int memberId, int hidden) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleBean bean = (ArticleBean) session
					.createQuery(UPDATE_ARTICLE).setParameter(0, articleId)
					.setParameter(1, memberId).uniqueResult();
			if (bean != null) {
				bean.setHidden(hidden);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void updateArticleHidden(int articleId, int hidden) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleBean bean = (ArticleBean) session.get(ArticleBean.class, articleId);
			if (bean != null) {
				bean.setHidden(hidden);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void updateReArticleCount(int reId, int articleId, ReArticleBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery(
					"update ArticleBean set re_article_count = ?, re_article_date = ? where article_id = ?")
					.setParameter(0, reId).setParameter(1, bean.getReMake())
					.setParameter(2, articleId).executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void updateBrowserCount(int articleId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleBean bean = (ArticleBean) session.get(ArticleBean.class, articleId);
			if (bean != null) {
				bean.setBrowserCount(bean.getBrowserCount() + 1);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public ArticleBean getOne(int articleId) {
		ArticleBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (ArticleBean) session.get(ArticleBean.class, articleId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return bean;
	}

	@Override
	public int getRecordCounts() {
		long count = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			count = (Long) session.createQuery(
					"Select count(*) From ArticleBean").uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return (int) count;
	}

	@Override
	public List<ArticleBean> getPageArticles(int startRow, int recordsPerRow) {
		List<ArticleBean> beans = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans = session
					.createQuery(
							"From ArticleBean order by re_article_date desc")
					.setFirstResult(startRow).setMaxResults(recordsPerRow)
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}

	@Override
	public int getRecordCounts(int articleClassNo) {
		long count = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			count = (Long) session
					.createQuery(
							"Select count(*) From ArticleBean where article_Class_no = ?")
					.setInteger(0, articleClassNo).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
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
			beans = session
					.createQuery(
							"From ArticleBean where article_class_no = ? order by re_article_date desc")
					.setInteger(0, articleClassNo).setFirstResult(startRow)
					.setMaxResults(recordsPerRow).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}

	@Override
	public int getRecordCounts(String articleTitle) {
		long count = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			count = (Long) session
					.createQuery(
							"Select count(*) From ArticleBean where article_title like ?")
					.setString(0, "%" + articleTitle + "%").uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
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
			beans = session
					.createQuery(
							"From ArticleBean where article_Title like ? order by re_article_date desc")
					.setString(0, "%" + articleTitle + "%")
					.setFirstResult(startRow).setMaxResults(recordsPerRow)
					.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}

	@Override
	public String getArticleTitle(int articleId) {
		ArticleBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (ArticleBean) session
					.createQuery("From ArticleBean where article_id = ?")
					.setInteger(0, articleId).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return bean.getArticleTitle();
	}

	@Override
	public int getBrowserCount(int articleId) {
		// TODO Auto-generated method stub
		return 0;
	}
}
