package com.point.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;



public class MemberHibernateservice {
	
	MemberHibernateDAO dao;
	public MemberHibernateservice() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		this.dao = new MemberHibernateDAO(session);
	}
	//抓會員點數
	public  MemberBean selectPoint(int point){
		return dao.selectPoint(point);
		
	}
	
	
	//抓單一會員
	public MemberBean select(int member_id){
	 return dao.select(member_id);

	}
	//扣會員點數
	public MemberBean updatePoint(int member_id){
		return dao.updatePoint(member_id);
		
	}
	
	
	
	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			MemberHibernateDAO dao = new MemberHibernateDAO(session);
			System.out.println(dao.updatePoint(3));
				
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}

	
		
	
	
	 
	
}
