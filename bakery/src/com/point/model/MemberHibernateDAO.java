package com.point.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.member.model.MemberBean;

import hibernate.util.HibernateUtil;

public class MemberHibernateDAO {
	static final String UPDATE_POINT = "Update MemberBean set point = point - 100 where Member_id=?";
	static final String UPDATE_POINT2 = "Update MemberBean set point = point +500 where Member_id=?";
	static final String UPDATE_POINT50 = "Update MemberBean set point = point -50 where Member_id=?";

	static final String UPDATE_POINT100 = "Update MemberBean set point = point + 100 where Member_id=?";
	static final String UPDATE_POINT200 = "Update MemberBean set point = point + 200 where Member_id=?";
	static final String UPDATE_POINT300 = "Update MemberBean set point = point + 300 where Member_id=?";

	// 點數扣100點
	public MemberBean updatePoint(int member_id) {
		MemberBean bean = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_POINT);
			query.setParameter(0, member_id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	// 增加點數
	public MemberBean updatePoint2(int member_id) {
		MemberBean bean = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery(UPDATE_POINT2);
			q.setParameter(0, member_id);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;

	}

	// 點數扣50點
	public MemberBean updatePoint50(int member_id) {
		MemberBean bean = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery(UPDATE_POINT50);
			q.setParameter(0, member_id);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;

	}

	// 增加點數100
	public MemberBean updatePoint100(int member_id) {
		MemberBean bean = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery(UPDATE_POINT100);
			q.setParameter(0, member_id);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	// 增加200點
	public MemberBean updatePoint200(int member_id) {
		MemberBean bean = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery(UPDATE_POINT200);
			q.setParameter(0, member_id);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	// 增加300點

	public MemberBean updatePoint300(int member_id) {
		MemberBean bean = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_POINT300);
			query.setParameter(0, member_id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public static void main(String[] args) {
		MemberBean bean = new MemberBean();
		List<MemberBean> beans = null;
		MemberHibernateDAO dao = new MemberHibernateDAO();
		// beans = dao.select();
		// System.out.println(dao.updatePoint(4));
		dao.updatePoint(1);

	}

}
