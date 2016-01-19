package com.Voteitem.model;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class VotePersonHibernate {

	
	
	
	public static void main (String args[]){

	}
	
	public void insert(VotePersonBean votepersonbean){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(votepersonbean);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	private static final String HQL_COUNT=" SELECT  Count(*) from VotePersonBean where VoteItemId= ?";
	public int select(int voteitemid){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int count=0;
		try {
			session.beginTransaction();
	        Query query = session.createQuery(HQL_COUNT);
	        query.setParameter(0, voteitemid);
	        count =(int)query.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return count;
	}
	
	
}
