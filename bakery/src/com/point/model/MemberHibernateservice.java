package com.point.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.member.model.MemberBean;

import hibernate.util.HibernateUtil;

public class MemberHibernateservice {

	MemberHibernateDAO dao= new MemberHibernateDAO();
	// 扣會員點數100
	public MemberBean updatePoint100(int member_id) {
		return dao.updatePoint100(member_id);

	}

	// 扣會員點數50
	public MemberBean updatePoint50(int member_id) {
		return dao.updatePoint50(member_id);

	}


	public static void main(String[] args) {
		// try {
		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		// session.beginTransaction();
		//
		// MemberHibernateDAO dao = new MemberHibernateDAO(session);
		// System.out.println(dao.updatePoint(1));
		//
		// session.getTransaction().commit();
		// } finally {
		// HibernateUtil.closeSessionFactory();
		// }
		MemberBean bean = new MemberBean();
		List<MemberBean> beans = null;
		MemberHibernateDAO dao = new MemberHibernateDAO();
		// beans = dao.select();
		// System.out.println(dao.updatePoint(4));
		dao.updatePoint100(1);

	}

}
