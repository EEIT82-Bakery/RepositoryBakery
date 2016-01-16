package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductBean;

public class MemberDAOJndi implements MemberDAO_Interface {

	private DataSource ds;

	public MemberDAOJndi() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GETPK = "SELECT * FROM MEMBER WHERE member_id=?";
	private static final String SELECT_ACCOUNT = "select * from Member where Account=?";
	private static final String GETALL = "SELECT * FROM MEMBER";
	private static final String UPDATE_IFMT="UPDATE MEMBER SET birth=?,phone=?,email=?,address=?,nickname=? WHERE MEMBER_ID=?";
	
	
	@Override
	public MemberBean getOne(int memberid) {
		MemberBean bean = null;
		ResultSet rest = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GETPK)){
			stmt.setInt(1, memberid);
			rest = stmt.executeQuery();
			if(rest.next()){
				bean = new MemberBean();
				bean.setMember_id(rest.getInt("Member_id"));
				bean.setAccount(rest.getString("Account"));
				bean.setPassword(rest.getBytes("Password"));
				bean.setUsername(rest.getString("Username"));
				bean.setSex(rest.getString("Sex"));
				bean.setBirth(rest.getDate("Birth"));
				bean.setEmail(rest.getString("Email"));
				bean.setPhone(rest.getString("Phone"));
				bean.setAddress(rest.getString("Address"));
				bean.setLogindate(rest.getDate("Logindate"));
				bean.setLast_date(rest.getTimestamp("Last_date"));
				bean.setFromfb(rest.getString("Fromfb"));
				bean.setPicture(rest.getBytes("Picture"));
				bean.setStatus(rest.getInt("Statu"));
				bean.setOrder_math(rest.getInt("Order_math"));
				bean.setNickname(rest.getString("Nickname"));
				bean.setKanban(rest.getString("Kanban"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}
	

	@Override
	public MemberBean getAccount(String account) {
		MemberBean bean = null;
		ResultSet rest = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ACCOUNT)){
			stmt.setString(1, account);
			rest = stmt.executeQuery();
			if(rest.next()){
				bean = new MemberBean();
				bean.setMember_id(rest.getInt("member_id"));
				bean.setAccount(rest.getString("Account"));
				bean.setPassword(rest.getBytes("Password"));
				bean.setUsername(rest.getString("Username"));
				bean.setSex(rest.getString("Sex"));
				bean.setBirth(rest.getDate("Birth"));
				bean.setEmail(rest.getString("Email"));
				bean.setPhone(rest.getString("Phone"));
				bean.setAddress(rest.getString("Address"));
				bean.setLogindate(rest.getDate("Logindate"));
				bean.setLast_date(rest.getTimestamp("Last_date"));
				bean.setFromfb(rest.getString("Fromfb"));
				bean.setPicture(rest.getBytes("Picture"));
				bean.setStatus(rest.getInt("Statu"));
				bean.setOrder_math(rest.getInt("Order_math"));
				bean.setNickname(rest.getString("Nickname"));
				bean.setKanban(rest.getString("Kanban"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}
	
	//取得全部
	private static final String SELECT_BY_ALL = "SELECT * FROM MEMBER";
	@Override
	public List<MemberBean> getALL() {
		List<MemberBean> result = null;

		ResultSet rest = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ALL);) {
			rest = stmt.executeQuery();
			result = new Vector<>();
			while (rest.next()) {
				MemberBean bean = new MemberBean();
				bean.setMember_id(rest.getInt("member_id"));
				bean.setAccount(rest.getString("Account"));
				bean.setPassword(rest.getBytes("Password"));
				bean.setUsername(rest.getString("Username"));
				bean.setSex(rest.getString("Sex"));
				bean.setBirth(rest.getDate("Birth"));
				bean.setEmail(rest.getString("Email"));
				bean.setPhone(rest.getString("Phone"));
				bean.setAddress(rest.getString("Address"));
				bean.setLogindate(rest.getDate("Logindate"));
				bean.setLast_date(rest.getTimestamp("Last_date"));
				bean.setFromfb(rest.getString("Fromfb"));
				bean.setPicture(rest.getBytes("Picture"));
				bean.setStatus(rest.getInt("Statu"));
				bean.setPoint(rest.getInt("Point"));
				bean.setOrder_math(rest.getInt("Order_math"));
				bean.setNickname(rest.getString("Nickname"));
				bean.setKanban(rest.getString("Kanban"));
				result.add(bean);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	//更新個人資訊
	@Override
	public MemberBean update(Date birth, String phone, String email, String address, String nickname, int memberid) {
		MemberBean bean = null;

		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_IFMT);){
			java.util.Date temp =birth;
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				stmt.setDate(1, someTime);
			} else {
				stmt.setDate(1, null);
			}
			
			stmt.setString(2, phone);
			stmt.setString(3,email);
			stmt.setString(4,address);
			stmt.setString(5, nickname);
			stmt.setInt(6, memberid);
			int i = stmt.executeUpdate();
			if(i==1){
				bean = this.getOne(memberid);
				System.out.println("MemberDAOJndi:"+bean);
				return bean;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("MemberDAOJndi_null:"+bean);
		return null;
	}

	//更改密碼
	private static final String UPDATE_PASSWORD = "UPDATE Member set password=? where Account=?";
	@Override
	public boolean update(byte[] password, String account) {

		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_PASSWORD);) {
			stmt.setBytes(1, password);
			stmt.setString(2, account);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	/*                           */
	
	//註冊帳號
	private static final String INSET_MEMBER = "INSERT INTO member(account,password,username,sex,birth,email,phone,address,last_date,picture,statu,point,order_math,nickname,kanban)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	@Override
	public MemberBean insert(MemberBean bean) {
	
		MemberBean result = null;

		int insertCount = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSET_MEMBER);) {
			stmt.setString(1, bean.getAccount());
			stmt.setBytes(2, bean.getPassword());
			stmt.setString(3, bean.getUsername());
			stmt.setString(4, bean.getSex());
			java.util.Date temp = bean.getBirth();
			if (temp != null) {
				java.sql.Date someTime = new java.sql.Date(temp.getTime());
				stmt.setDate(5, someTime);
			} else {
				stmt.setDate(5, null);
			}
			stmt.setString(6, bean.getEmail());
			stmt.setString(7, bean.getPhone());
			stmt.setString(8, bean.getAddress());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(9, ts);
			stmt.setBytes(10, bean.getPicture());
			stmt.setInt(11, bean.getStatus());
			stmt.setInt(12, bean.getPoint());
			stmt.setInt(13, bean.getOrder_math());
			stmt.setString(14, bean.getNickname());
			stmt.setString(15, bean.getKanban());
			insertCount = stmt.executeUpdate();
			if (insertCount == 1) {
				result = bean;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	//------------------------------------------------------------
	//註冊時檢查有無此帳號
	private static final String ACCOUNT_EXIST = "select * from Member where Account like ?";
	@Override
	public boolean idExists(String account) {
		ResultSet rest = null;
		try (Connection conn = ds.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(ACCOUNT_EXIST);) {
			stmt.setString(1, account);
			rest = stmt.executeQuery();
			if (rest.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	//以帳號查詢資訊
	private static final String SELECT_BY_ACCOUNT = "select * from member where Account=?";
	@Override
	public MemberBean findByAccount(String account) {
		MemberBean bean = null;
		ResultSet rest = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ACCOUNT);) {
			stmt.setString(1, account);
			rest = stmt.executeQuery();
			if (rest.next()) {
				bean = new MemberBean();
				bean.setMember_id(rest.getInt("member_id"));
				bean.setAccount(rest.getString("Account"));
				bean.setPassword(rest.getBytes("Password"));
				bean.setUsername(rest.getString("Username"));
				bean.setSex(rest.getString("Sex"));
				bean.setBirth(rest.getDate("Birth"));
				bean.setEmail(rest.getString("Email"));
				bean.setPhone(rest.getString("Phone"));
				bean.setAddress(rest.getString("Address"));
				bean.setLogindate(rest.getDate("Logindate"));
				bean.setLast_date(rest.getTimestamp("Last_date"));
				bean.setFromfb(rest.getString("Fromfb"));
				bean.setPicture(rest.getBytes("Picture"));
				bean.setStatus(rest.getInt("Statu"));
				bean.setPoint(rest.getInt("Point"));
				bean.setOrder_math(rest.getInt("Order_math"));
				bean.setNickname(rest.getString("Nickname"));
				bean.setKanban(rest.getString("Kanban"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	private static final String SELECT_BYACCOUNT = "select * from member where Account=?";
	@Override
	public List<MemberBean> getAllMem(String account) {
		List<MemberBean> result = null;
		ResultSet rest = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_BYACCOUNT);) {
			stmt.setString(1, account);
			rest = stmt.executeQuery();
			result = new Vector<>();
			while (rest.next()) {
				MemberBean bean = new MemberBean();
				bean.setMember_id(rest.getInt("member_id"));
				bean.setAccount(rest.getString("Account"));
				bean.setPassword(rest.getBytes("Password"));
				bean.setUsername(rest.getString("Username"));
				bean.setSex(rest.getString("Sex"));
				bean.setBirth(rest.getDate("Birth"));
				bean.setEmail(rest.getString("Email"));
				bean.setPhone(rest.getString("Phone"));
				bean.setAddress(rest.getString("Address"));
				bean.setLogindate(rest.getDate("Logindate"));
				bean.setLast_date(rest.getTimestamp("Last_date"));
				bean.setFromfb(rest.getString("Fromfb"));
				bean.setPicture(rest.getBytes("Picture"));
				bean.setStatus(rest.getInt("Statu"));
				bean.setPoint(rest.getInt("Point"));
				bean.setOrder_math(rest.getInt("Order_math"));
				bean.setNickname(rest.getString("Nickname"));
				bean.setKanban(rest.getString("Kanban"));
				result.add(bean);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String GET_ONE_STATUS = "  SELECT Member_id,Account,Password,Username,Sex,Birth,Email,Phone,Address,Logindate,Last_date,Fromfb,Picture,mg.Statu,"
			+ "Order_math,Nickname,mg.Permname , Kanban	From Member m join Member_grade mg "
			+ "on  m.Statu = mg.Statu Where account like ?";
	@Override
	public MemberBean getMember(String account) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		MemberBean bean = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STATUS);
			pstmt.setString(1, account);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
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
				bean.setPermname(rs.getString("Permname"));
				bean.setKanban(rs.getString("Kanban"));
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	
	//***更新動態狀態
	private static final String UPDATE_KANBAN = "update member set Kanban=? where WHERE MEMBER_ID=?";
	@Override
	public MemberBean update(String kanban,int memberid) {
		MemberBean bean = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_KANBAN);){
				stmt.setString(1, kanban);
				stmt.setInt(2, memberid);
				int i = stmt.executeUpdate();
				if(i==1){
					bean = this.getOne(memberid);
					System.out.println("MemberDAOJndi:"+bean);
					return bean;
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			System.out.println("MemberDAOJndi_null:"+bean);
			return null;
		}


	
	
	
	
	@Override
	public List<MemberBean> selectByPage(int pageInt, int status) {
		List<MemberBean> result = null;
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("select * FROM member where statu = "+status+" ORDER BY Member_id OFFSET 5 * (" + (pageInt - 1) + ") ROWS FETCH NEXT 6 ROWS ONLY");
			rset = stmt.executeQuery();
			result = new ArrayList<MemberBean>();
			while (rset.next()) {
				MemberBean bean = new MemberBean();
				bean.setMember_id(rset.getInt("Member_id"));
				bean.setAccount(rset.getString("Account"));
				bean.setPassword(rset.getBytes("Password"));
				bean.setUsername(rset.getString("Username"));
				bean.setSex(rset.getString("Sex"));
				bean.setBirth(rset.getDate("Birth"));
				bean.setEmail(rset.getString("Email"));
				bean.setPhone(rset.getString("Phone"));
				bean.setAddress(rset.getString("Address"));
				bean.setLogindate(rset.getDate("Logindate"));
				bean.setLast_date(rset.getTimestamp("Last_date"));
				bean.setFromfb(rset.getString("Fromfb"));
				bean.setPicture(rset.getBytes("Picture"));
				bean.setStatus(rset.getInt("Statu"));
				bean.setOrder_math(rset.getInt("Order_math"));
				bean.setNickname(rset.getString("Nickname"));
				bean.setPermname(rset.getString("Permname"));
				bean.setKanban(rset.getString("Kanban"));
			
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	return result;
	}
	


	private static final String memberStatus="SELECT count(member_id)  from member where statu=?";
	
	@Override
	public int getMemberStatus(int status) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int result=0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(memberStatus);
			pstmt.setInt(1, status);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}


	@Override
	public List<MemberBean> selectAllPage(int pageInt) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getAllMember() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
	
	
	
	
}
