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
	
		return null;
	}

	@Override
	public MemberBean getAccount(String account) {
		Session session = null;
		MemberBean bean = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();		
			Query query = session.createQuery("from MemberBean where account=:acconut");
			query.setParameter("account", account);
			bean = (MemberBean) query.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}	
		return bean;
	}
	@Override
	public List<MemberBean> getALL() {

		return null;
	}

	@Override
	public MemberBean update(Date birth, String phone, String email,
			String address, String nickname, int memberid) {
	
		return null;
	}

	@Override
	public List<MemberBean> getAllMem(String account) {
	
		return null;
	}

	@Override
	public boolean update(byte[] password, String account) {
	
		return false;
	}

	@Override
	public MemberBean insert(MemberBean bean) {
		
		return null;
	}

	@Override
	public boolean idExists(String account) {
		
		return false;
	}

	@Override
	public MemberBean findByAccount(String account) {
		Session session = null;
		MemberBean bean = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			
			
			
			
		
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return bean;
	}

	@Override
	public MemberBean getMember(String account) {
		Session session = null;
		MemberBean bean = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from MemberBean where account = ?").setString(0, account);
			bean = (MemberBean) query.uniqueResult();
		
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return bean;
	}

	@Override
	public MemberBean update(String kanban, int memberid) {
		Session session = null;
		MemberBean bean = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			
			
			
			
		
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<MemberBean> selectByPage(int pageInt, int status) {
		Session session = null;
		MemberBean bean = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			
			
			
			
		
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public int getMemberStatus(int status) {
		Session session = null;
		int statu= 0;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			

			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return statu;
	}

	@Override
	public List<MemberBean> selectAllPage(int pageInt) {
		Session session = null;
		List<MemberBean> beans = null;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();		
			Query query = session.createQuery("From MemberBean order by Member_id");
			query.setFirstResult((pageInt-1)*5);
			query.setMaxResults(5);
			beans = query.list();
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return beans;
	}

	@Override
	public int getAllMember() {
		Session session = null;
		long list = 0;
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query =  session.createQuery("Select count(*) From MemberBean");
			list = (Long) query.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}
		return (int) list;
	}

}
