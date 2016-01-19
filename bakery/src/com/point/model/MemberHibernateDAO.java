package com.point.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;




public class MemberHibernateDAO   {
	private  Session session;
	public MemberHibernateDAO(Session session){
		this.session = session;
	}
	public Session getSession() {
		return session;
	}



	static final String UPDATE_POINT= "Update member set point = point - 100 where Member_id = ?";
	static final String UPDATE_Point2="Update Member set point = point +500 where Member_id=?";
	
	
	//查詢全部
	public List<MemberBean> select(){
		List<MemberBean> b=null;
		MemberBean a = null;
				 Query query  =  session.createQuery("from MemberBean");
				b = query.list();
		return b;
		
	}

	//查詢id
	public MemberBean  select(int member_id){
		MemberBean a= null;
		
			
				a = (MemberBean) session.get(MemberBean.class ,member_id);
			
		return a;
	}	
	

	//修改點數
	public MemberBean update(int point,int member_id){
		MemberBean a =  null;
			
			a=(MemberBean) session.get(MemberBean.class, member_id);
			
			if(a!=null){
				a.setPoint(point);
							
			}
			return a;
	}
	
	//點數扣100點
	public MemberBean updatePoint(int member_id){
		MemberBean baean =  null;
        Query query = getSession().createQuery(UPDATE_POINT);
	    query.setParameter(0,member_id);
	    query.executeUpdate();
		return baean;
	
	}
	//增加點數 
	public  MemberBean updatePoint2(int member_id){
		MemberBean bean = null;
		Query q =session.createQuery(UPDATE_Point2);
		q.setParameter(0, member_id);
		q.executeUpdate();		
		return bean;
		
	}
	//查詢點數
	public  MemberBean selectPoint(int point){
		MemberBean a = null ;
				a=(MemberBean) session.get(MemberBean.class,point);
				
	
		
		return a;
	}
	
	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			MemberHibernateDAO dao = new MemberHibernateDAO(session);
			System.out.println(dao.updatePoint2(2));
				
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}
	
	
	
	
//	
//	public static void main (String[] args){
//		
//		MemberBean bean =new  MemberBean();
//		List<MemberBean> beans= null;
//		MemberHibernateDAO dao= new MemberHibernateDAO( session);
////		beans = dao.select();
////		System.out.println(beans);
//		dao.updatePoint(3);
//	
// }
//	
}
