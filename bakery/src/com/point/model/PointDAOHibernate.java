package com.point.model;

import hibernate.util.HibernateUtil;

import org.hibernate.Session;

import com.member.model.MemberBean;

public class PointDAOHibernate implements PointDAO_interface {

	public MemberBean updatePoint(int member_id, int point) {
		MemberBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (MemberBean) session.get(MemberBean.class, member_id);
			if (bean != null) {
				bean.setPoint(bean.getPoint() + point);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return bean;
	}

}
