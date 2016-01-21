package com.member.model;

import hibernate.util.HibernateUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class MemberDAOHibernate implements MemberDAO_Interface {

	@Override
	public MemberBean getOne(int memberid) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberBean bean = null;
		try {
			session.beginTransaction();
			bean =  (MemberBean) session.get(MemberBean.class, memberid);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@Override
	public MemberBean getAccount(String account) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("from MemberBean where account=:acconut");
			query.setParameter("account", account);
			bean = (MemberBean) query.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getALL() {
		Session session = null;
		List<MemberBean> list = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			list = session.createQuery("from MemberBean").list();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public MemberBean update(String phone, String email, String address,
			String nickname, byte[] picture, int memberid) {
		MemberBean bean = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			bean = (MemberBean) session.get(MemberBean.class, memberid);
			if (bean != null) {
				bean.setPhone(phone);
				bean.setEmail(email);
				bean.setAddress(address);
				bean.setNickname(nickname);
				bean.setPicture(picture);
				session.update(bean);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	private static final String SELECT_ACC = "FROM MemberBean WHERE account=?";

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getAllMem(String account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<MemberBean> lists = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(SELECT_ACC);
			lists = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return lists;

	}

	private static final String UPDATE_PWD = "UPDATE MemberBean SET Password=? WHERE Account= ?";

	@Override
	public boolean update(byte[] password, String account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_PWD);
			query.setParameter(0, password);
			query.setParameter(1, account);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}

		return true;
	}

	@Override
	public MemberBean insert(MemberBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bean);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@Override
	public boolean idExists(String account) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return true;
	}

	@Override
	public MemberBean findByAccount(String account) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@Override
	public MemberBean getMember(String account) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from MemberBean where account = ?").setString(0, account);
			bean = (MemberBean) query.uniqueResult();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@Override
	public MemberBean update(String kanban, int memberid) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	@Override
	public List<MemberBean> selectByPage(int pageInt, int status) {
		Session session = null;
		List<MemberBean> bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			session.createQuery("From MemberBean order by re_article_date desc")
					.setFirstResult(pageInt).setMaxResults(5).list();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

	private static final String SELECT_COUNT_BY_STATU = "Select count(*) From MemberBean WHERE Statu=:xxx";

	@Override
	public int getMemberStatus(int status) {
		Session session = null;
		int statu = 0;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(SELECT_COUNT_BY_STATU);
			query.setParameter("xxx", status);
			statu = (int) query.uniqueResult();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return (int) statu;
	}

	@Override
	public List<MemberBean> selectAllPage(int pageInt) {
		Session session = null;
		List<MemberBean> beans = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("From MemberBean order by Member_id");
			query.setFirstResult((pageInt - 1) * 5);
			query.setMaxResults(5);
			beans = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return beans;
	}

	@Override
	public int getAllMember() {
		Session session = null;
		long list = 0;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("Select count(*) From MemberBean");
			list = (Long) query.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		return (int) list;
	}

	@Override
	public void updateUnifom(MemberBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_PWD);
			query.setParameter(0, bean.getPassword());
			query.setParameter(1, bean.getAccount());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
	}

	private static final String GET_BY_ID = "FROM MemberBean WHERE member_id = ?";

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> selectAllByid(int memberid) {
		List<MemberBean> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_BY_ID);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getAll(Map<String, String[]> map) {
		List<MemberBean> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Criteria query = session.createCriteria(MemberBean.class);
			Set<String> keys = map.keySet();
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0
						&& !"action".equals(key)) {
					query = MemberCompositeQuery.getCriteria(query, key, value);
				}
			}
			query.addOrder(Order.asc("Member_id"));
			list = query.list();

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public void changePassword(MemberBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_PWD);
			query.setParameter(0, bean.getPassword());
			query.setParameter(1, bean.getAccount());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;

		}
	}

	@Override
	public boolean emailExists(String email) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("from MemberBean where email=:email");
			query.setParameter("email", email);

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return true;
	}

	private final static String selectPwd = "From MemberBean where email = ? and account= ?";

	@Override
	public MemberBean selectPassword(String account, String email) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MemberBean bean = null;
		try {
			session.beginTransaction();

			bean = (MemberBean) session.createQuery(selectPwd)
					.setParameter(0, email).setParameter(1, account)
					.uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return bean;
	}

	private static final String UPDATE_STATE = "UPDATE MemberBean SET Statu = ? WHERE Member_id = ?";

	@Override
	public void updateState(MemberBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(UPDATE_STATE);
			query.setParameter(0, bean.getStatus());
			query.setParameter(1, bean.getMember_id());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}

	}

	private static final String GET_ALL_ACCOUNT = "SELECT Account FROM MemberBean";

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllAccount() {
		List<String> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_ACCOUNT);
			list = query.list();

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public MemberBean getMemberEmail(String account, String email) {
		Session session = null;
		MemberBean bean = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery(
							"from MemberBean where account = ? and email=?")
					.setString(0, account).setString(1, email);
			bean = (MemberBean) query.uniqueResult();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return bean;
	}

}
