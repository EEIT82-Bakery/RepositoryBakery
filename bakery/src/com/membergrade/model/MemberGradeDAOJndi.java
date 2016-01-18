package com.membergrade.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberBean;

public class MemberGradeDAOJndi implements MemberGradeDAO_Interface {
	
	private DataSource ds;

	public MemberGradeDAOJndi() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String UPDATE = "UPDATE member_grade set permname=? where statu = ?";
	@Override
	public void update(MemberGradeBean memberGradeBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, memberGradeBean.getPermname());
			pstmt.setInt(2, memberGradeBean.getStatus());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	
	
	
	private static final String DELETE_MEMBERS = "DELETE FROM member where statu = ?";
	private static final String DELETE_STATUS = "DELETE FROM member_grade where statu = ?";	
	@Override
	public void delete(int status) {
		int updateCount_member = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE_MEMBERS);
			pstmt.setInt(1,status);
			updateCount_member = pstmt.executeUpdate();
			pstmt = con.prepareStatement(DELETE_STATUS);
			pstmt.setInt(1, status);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除狀態編號" + status + "時,共有會員" + updateCount_member
					+ "位會員同時被刪除");
			
	}catch (SQLException se) {
		if (con != null) {
			try {
				// 3●設定於當有exception發生時之catch區塊內
				con.rollback();
			} catch (SQLException excep) {
				throw new RuntimeException("rollback error occured. "
						+ excep.getMessage());
			}
		}
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

}

	
	
	
	private static final String GET_ONE_STMT = "SELECT statu , permname FROM member_grade where statu = ?";
	@Override
	public MemberGradeBean findByPrimaryKey(int status) {
		MemberGradeBean bean = null;
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rest = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, status);
			rest = pstmt.executeQuery();
			if(rest.next()){
				bean = new MemberGradeBean();
				bean.setStatus(rest.getInt("Statu"));
				bean.setPermname(rest.getString("Permname"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bean;
	}
	
	
	
	private static final String GET_ALL_STMT = "SELECT statu , permname FROM member_grade";
	@Override
	public List<MemberGradeBean> getAll() {
		List<MemberGradeBean> list = new ArrayList<>();
		MemberGradeBean bean = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		try {

			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rest = pstmt.executeQuery();

			while (rest.next()) {
				bean = new MemberGradeBean();
				bean.setStatus(rest.getInt("Statu"));
				bean.setPermname(rest.getString("Permname"));
				list.add(bean); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
		

	private static final String GET_Members_ByStatus_STMT = "SELECT Member_id,Account,Password,Username,Sex,Birth,Email,Phone,Address,Logindate,Last_date,Fromfb,Picture,Statu,Order_math,Nickname FROM member where Statu = ? order by Member_id";
	@Override
	public Set<MemberBean> getEmpsByDeptno(int status) {
		 Set<MemberBean> set = new  LinkedHashSet<MemberBean>();
		 MemberBean bean = null;
		 ResultSet rs = null;
		 try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_Members_ByStatus_STMT)){
			 pstmt.setInt(1,status);
			 rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new MemberBean();
					bean.setMember_id(rs.getInt("Member_id"));
					bean.setAccount(rs.getString("Account"));
					bean.setPassword(rs.getBytes("Password"));
					bean.setUsername(rs.getString("Username"));
					bean.setSex(rs.getString("Sex"));
					bean.setBirth(rs.getDate("Birth"));
					bean.setEmail(rs.getString("Email"));
					bean.setPhone(rs.getString("Phone"));
					bean.setAddress(rs.getString("Address"));
					bean.setLogindate(rs.getDate("Logindate"));
					bean.setLast_date(rs.getTimestamp("Last_date"));
					bean.setFromfb(rs.getString("Fromfb"));
					bean.setPicture(rs.getBytes("Picture"));
					bean.setStatus(rs.getInt("Statu"));
					bean.setOrder_math(rs.getInt("Order_math"));
					bean.setNickname(rs.getString("Nickname"));
					set.add(bean);
					}
				}catch (SQLException se) {
					throw new RuntimeException("A database error occured. "
							+ se.getMessage());
				}finally{
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
		 
		return set;
	}

}
