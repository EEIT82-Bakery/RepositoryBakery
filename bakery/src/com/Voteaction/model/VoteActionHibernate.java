package com.Voteaction.model;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class VoteActionHibernate implements VoteAction_Interface{

	public static void main (String args[]){
		VoteActionHibernate test=new VoteActionHibernate();
		VoteActionBean bean= new VoteActionBean();
		bean.setVoteTitle("開始活動2");
		bean.setVoteDescribe("這是一個端午節活動2");
		bean.setVoteStartDate(java.sql.Date.valueOf("2013-05-26"));
		bean.setVoteEndDate(java.sql.Date.valueOf("2015-05-16"));
		bean.setVoteStatus(1);
		test.insert(bean);
//		System.out.println(test.selectPk(2));
//		test.update("開始活動", "這是一個端午節活動",java.sql.Date.valueOf("2015-10-16") , 1, 1);
//		test.delete(1);
//		System.out.println(test.selectall());
	}

	
	@Override
	public void insert(VoteActionBean bean) {
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
		
	@Override
	public void update(String title, String describe, Date end,int voteStatus,int voteid) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		  try { 
		   session.beginTransaction(); 
		   VoteActionBean bean = (VoteActionBean) session.get(VoteActionBean.class,voteid);  
		   if(bean != null){ 
		    bean.setVoteTitle(title); 
		    bean.setVoteDescribe(describe); 
		    bean.setVoteEndDate(end); 
		    bean.setVoteStatus(voteStatus);
		    session.update(bean); 
		   } 
		   session.getTransaction().commit(); 
		  } catch (HibernateException e) { 
		   e.printStackTrace(); 
		   session.getTransaction().rollback(); 
		  }
	}

	@Override
	public void delete(int voteId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		  try { 
		   session.beginTransaction(); 
		   VoteActionBean bean = (VoteActionBean) session.get(VoteActionBean.class,voteId);  
		   if(bean != null){ 
		    session.delete(bean); 
		   } 
		   session.getTransaction().commit(); 
		  } catch (HibernateException e) { 
		   e.printStackTrace(); 
		   session.getTransaction().rollback(); 
		  }
	}
	@Override
	public List<VoteActionBean> selectall() {
		List<VoteActionBean> beans =null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		  try { 
		   session.beginTransaction(); 
		   Query query = session.createQuery("from VoteActionBean");
		  beans=query.list();
		   session.getTransaction().commit(); 
		  } catch (HibernateException e) { 
		   e.printStackTrace(); 
		   session.getTransaction().rollback(); 
		  }
		return beans;
	}

	@Override
	public VoteActionBean selectPk(int VoteId) {
		VoteActionBean bean =null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
		  try { 
		   session.beginTransaction(); 
		   bean = (VoteActionBean) session.get(VoteActionBean.class,VoteId);  
		   session.getTransaction().commit(); 
		  } catch (HibernateException e) { 
		   e.printStackTrace(); 
		   session.getTransaction().rollback(); 
		  }
		return bean;
	}

}
