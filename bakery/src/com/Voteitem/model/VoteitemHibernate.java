package com.Voteitem.model;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.Voteaction.model.VoteActionBean;
import com.Voteaction.model.VoteActionHibernate;

import hibernate.util.HibernateUtil;

public class VoteitemHibernate implements Voteitem_Interface{
	

//	public static void main (String args[]){
//		VoteitemHibernate test=new VoteitemHibernate();
//		VoteitemBean bean= new VoteitemBean();
//
//		bean.setVoteItemName("開始活動2");
//		bean.setVoteItemPhoto(null);
//		bean.setVoteId(2);
//		
//
//	}

	
	
	@Override
	public void insert(VoteitemBean bean){
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

	private static final String SELECT_LIST_ID="from VoteitemBean where voteId=?";
	public List<VoteitemBean> selectitem(int Vote_id){
		List<VoteitemBean> beans=null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			beans=(List<VoteitemBean>)session.createQuery(SELECT_LIST_ID)
					.setParameter(0,Vote_id)
					.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return beans;
	}

	@Override
	public void delete(int Vote_item_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			VoteitemBean bean=(VoteitemBean)session.get(VoteitemBean.class,Vote_item_id);
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
	public List<VoteitemBean> selectAll(VoteActionBean Vote_id) {
		
		return null;
	}
	
	
}
