package com.point.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.member.model.MemberBean;

import hibernate.util.HibernateUtil;

public class MemberHibernateDAO {

	// private Session session;
	// public Session getSession() {
	// return session ;
	//
	// }

	// static final String UPDATE_POINT = "Update MemberBean set point = point -
	// 100 where Member_id=?";
	// static final String UPDATE_POINT2 = "Update MemberBean set point = point
	// +500 where Member_id=?";
	// static final String UPDATE_POINT50 = "Update MemberBean set point = point
	// -50 where Member_id=?";
	//
	// static final String UPDATE_POINT100 = "Update MemberBean set point =
	// point + 100 where Member_id=?";
	// static final String UPDATE_POINT200 = "Update MemberBean set point =
	// point + 200 where Member_id=?";
	// static final String UPDATE_POINT300 = "Update MemberBean set point =
	// point + 300 where Member_id=?";

	// 點數扣100點
	public MemberBean updatePoint100(int member_id) {
		MemberBean a=null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			a = (MemberBean) session.get(MemberBean.class, member_id);
//			System.out.println("a=" + a);
			if (a != null) {
				a.setPoint(a.getPoint() - 100);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	
	}

	// 增加點數500
	public MemberBean updatePoint500(int member_id) {
		MemberBean a=null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			 a = (MemberBean) session.get(MemberBean.class, member_id);

			if (a != null) {
				a.setPoint(a.getPoint() + 500);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}

	// 點數扣50點
	public MemberBean updatePoint50(int member_id) {
		MemberBean a=null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			a = (MemberBean) session.get(MemberBean.class, member_id);
			if (a != null) {
				a.setPoint(a.getPoint() - 50);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	//+100點
	public MemberBean updatePoint100p(int member_id) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			MemberBean a = (MemberBean) session.get(MemberBean.class, member_id);
			if (a != null) {
				a.setPoint(a.getPoint() + 100);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		//+200點
	public MemberBean updatePoint200p(int member_id) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			MemberBean a = (MemberBean) session.get(MemberBean.class, member_id);
			if (a != null) {
				a.setPoint(a.getPoint() + 200);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//+300點
	public MemberBean updatePoint300p(int member_id) {

		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			MemberBean a = (MemberBean) session.get(MemberBean.class, member_id);
			if (a != null) {
				a.setPoint(a.getPoint() + 500);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
		
	

	public static void main(String[] args) {
		MemberBean bean = new MemberBean();
		List<MemberBean> beans = null;
		MemberHibernateDAO dao = new MemberHibernateDAO();
		// beans = dao.select();
		System.out.println(dao.updatePoint100(1));
		// dao.updatePoint100(1);

	}

}
