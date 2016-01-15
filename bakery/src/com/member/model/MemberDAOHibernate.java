package com.member.model;

import hibernate.util.HibernateUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class MemberDAOHibernate implements MemberDAO_Interface {

	@Override
	public MemberBean getOne(int memberid) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}

		return bean;
	}

	@Override
	public MemberBean getAccount(String account) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("from MemberBean where account=:acconut");
			query.setParameter("account", account);
			bean = (MemberBean) query.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		return bean;
	}

	@Override
	public List<MemberBean> getALL() {
		Session session = null;
		List<MemberBean> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from MemberBean ORDER BY Member_id");
			list = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public MemberBean update(Date birth, String phone, String email,
			String address, String nickname, int memberid) {

		
		
		
		return null;
	}

	@Override
	public List<MemberBean> getAllMem(String account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		return null;
		
	}

	private static final String UPDATE_PWD = "UPDATE MEMBERBEAN SET Password=? WHERE Account= ?";
	@Override
	public boolean update(byte[] password, String account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_PWD);
			query.setParameter(0, password);
			query.setParameter(1, account);
			query.executeUpdate();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
		}
	
		return true;
	}

	@Override
	public MemberBean insert(MemberBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return bean;
	}

	@Override
	public boolean idExists(String account) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		return false;
	}

	@Override
	public MemberBean findByAccount(String account) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@Override
	public MemberBean getMember(String account) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from MemberBean where account = ?").setString(0, account);
			bean = (MemberBean) query.uniqueResult();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@Override
	public MemberBean update(String kanban, int memberid) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return null;
	}

	@Override
	public List<MemberBean> selectByPage(int pageInt, int status) {
		Session session = null;
		List<MemberBean> bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.createQuery(
					"From MemberBean order by re_article_date desc")
					.setFirstResult(pageInt).setMaxResults(5)
					.list();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	private static final String SELECT_COUNT_BY_STATU = "Select count(*) From MemberBean WHERE Statu=:xxx";

	@Override
	public int getMemberStatus(int status) {
		Session session = null;
		int statu = 0;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(SELECT_COUNT_BY_STATU);
			query.setParameter("xxx", status);
			statu = (int) query.uniqueResult();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return (int) statu;
	}

	@Override
	public List<MemberBean> selectAllPage(int pageInt) {
		Session session = null;
		List<MemberBean> beans = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("From MemberBean order by Member_id");
			query.setFirstResult((pageInt - 1) * 5);
			query.setMaxResults(5);
			beans = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return beans;
	}

	@Override
	public int getAllMember() {
		Session session = null;
		long list = 0;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("Select count(*) From MemberBean");
			list = (Long) query.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		return (int) list;
	}

	@Override
	public void updateUnifom(MemberBean bean) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_PWD);
			query.setParameter(0, bean.getPassword());
			query.setParameter(1, bean.getAccount());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
		
	

}
