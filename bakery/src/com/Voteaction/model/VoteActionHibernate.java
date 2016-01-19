package com.Voteaction.model;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class VoteActionHibernate implements VoteAction_Interface{

	public static void main (String args[]){


		VoteActionHibernate voteaction=new VoteActionHibernate();
//			System.out.println(	voteaction.selectall());
//		VoteActionBean bean = new VoteActionBean();
//		bean.setVoteTitle("我愛投票");
//		bean.setVoteDescribe("給我一個投票的理由");
//		bean.setVoteStatus(2);
//		System.out.println("pk:"+ voteaction.insertreturnInt(bean));
		}
	public Integer insertGetPk(VoteActionBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int pk=0;
		try {
			session.beginTransaction();
			pk= (int)session.save(bean);
			session.getTransaction().commit();
			return pk;

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return pk;
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
	

}
