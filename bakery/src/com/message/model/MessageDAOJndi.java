package com.message.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MessageDAOJndi implements MessageDAO {
	private DataSource ds;
	public MessageDAOJndi() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String SELECT="SELECT Msg_id, Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state From Message WHERE Send_id=? and Read_id=? and Msg_date=?";
	@Override
	public MessageBean select(Integer send_id, Integer read_id,Timestamp msg_date) {
		MessageBean bean = null;
		ResultSet rs = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT);){
			stmt.setInt(1, send_id);
			stmt.setInt(2, read_id);
			stmt.setTimestamp(3, msg_date);
			rs = stmt.executeQuery();
			while(rs.next()){
				bean = new MessageBean();
				bean.setSend_id(rs.getInt("Send_id"));
				bean.setRead_id(rs.getInt("Read_id"));
				bean.setMsg_tit(rs.getString("Msg_tit"));
				bean.setMsg_cont(rs.getString("Msg_cont"));
				bean.setMsg_date(rs.getTimestamp("Msg_date"));
				bean.setMsg_state(rs.getInt("Msg_state"));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return bean;
		}
	private static final String INSERT = "INSERT INTO Message (Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state) values(?,?,?,?,?,?)";
	
	@Override
	public int insert(MessageBean bean) {
		int updateCount = 0;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(INSERT);){
				pstmt.setInt(1, bean.getSend_id());
				pstmt.setInt(2,bean.getRead_id());
				pstmt.setString(3,bean.getMsg_tit());
				pstmt.setString(4,bean.getMsg_cont());
				pstmt.setTimestamp(5, bean.getMsg_date());
				pstmt.setInt(6,bean.getMsg_state());		
			updateCount = pstmt.executeUpdate();
						
				}catch (Exception e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			}
			return updateCount;
	}
	private static final String DELETE = "DELETE FROM Message where Msg_id";
	@Override
	public int delete(Integer msg_id) {
		int updateCount = 0;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(DELETE);){
				pstmt.setInt(1, msg_id);
			updateCount = pstmt.executeUpdate();
				}catch (Exception e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
			}
			return updateCount;
	}	
	private static final String UPDATE = "UPDATE Message set Msg_state=? where Send_id=? and Read_id=? and Msg_date=?";
	@Override
	public int update(MessageBean bean) {
		int updateCount = 0;
		try (Connection conn = ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(UPDATE)){
			pstmt.setInt(1, bean.getMsg_state());
			pstmt.setInt(2, bean.getSend_id());
			pstmt.setInt(3, bean.getRead_id());
			pstmt.setTimestamp(4, bean.getMsg_date());
			updateCount = pstmt.executeUpdate();

		} catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		}
		return updateCount;
	}
	
	private static final String SELECTALL="SELECT Msg_id,Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state From Message";
	@Override
	public List<MessageBean> selectAll() {
		List<MessageBean> list = null;
		ResultSet rs = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECTALL)){
			rs = stmt.executeQuery();
			list =  new ArrayList<MessageBean>();
			while(rs.next()){
				MessageBean bean  = new MessageBean();
				bean.setMsg_id(rs.getInt("Msg_id"));
				bean.setSend_id(rs.getInt("Send_id"));
				bean.setRead_id(rs.getInt("Read_id"));
				bean.setMsg_tit(rs.getString("Msg_tit"));
				bean.setMsg_cont(rs.getString("Msg_cont"));
				bean.setMsg_date(rs.getTimestamp("Msg_date"));
				bean.setMsg_state(rs.getInt("Msg_state"));
				list.add(bean);
				}
			}catch (Exception se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			
			}finally{
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}
		return list;
	}
	
	private static final String SELECTSTATE_a="SELECT *FROM MESSAGE WHERE read_ID=? AND  (MSG_STATE=1 or MSG_STATE=2) ORDER BY msg_date desc";
	private static final String SELECTSTATE = "SELECT * FROM Message where Read_id=?,Msg_state=?";
	@Override
	public List<MessageBean> getgivemymsg(Integer read_id, Integer msg_state) {
		List<MessageBean> list = null;
		ResultSet rs = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECTSTATE_a)){
			stmt.setInt(1, read_id);
			rs = stmt.executeQuery();
			list = new ArrayList<MessageBean>();
			while(rs.next()){
				MessageBean bean  = new MessageBean();
				bean.setMsg_id(rs.getInt("Msg_id"));
				bean.setSend_id(rs.getInt("Send_id"));
				bean.setRead_id(rs.getInt("Read_id"));
				bean.setMsg_tit(rs.getString("Msg_tit"));
				bean.setMsg_cont(rs.getString("Msg_cont"));
				bean.setMsg_date(rs.getTimestamp("Msg_date"));
				bean.setMsg_state(rs.getInt("Msg_state"));
				list.add(bean);	
				}
			}catch (Exception se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally{
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}
		return list;
	}
	//以狀態分頁
	@Override
	public List<MessageBean> selectPage(int pageInt,Integer read_id,Integer msg_state) {
		List<MessageBean> list = null;
		ResultSet rs = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(" select m1.account as 'sendAccount', m2.Account as 'readAccount' ,Msg_id , Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state from message msg join Member m1 on msg.Send_id = m1.Member_id  join Member m2 on msg.Read_id = m2.Member_id where Read_id="+read_id+"and Msg_state="+msg_state
					+ " ORDER BY Msg_state OFFSET 5 * (" + (pageInt - 1) + ") ROWS FETCH NEXT 5 ROWS ONLY")){
			rs = stmt.executeQuery();
			list = new ArrayList<MessageBean>();
			while(rs.next()){
				MessageBean bean  = new MessageBean();
				bean.setMsg_id(rs.getInt("Msg_id"));
				bean.setSend_id(rs.getInt("Send_id"));
				bean.setRead_id(rs.getInt("Read_id"));
				bean.setSendAccount(rs.getString("sendAccount"));
				bean.setReadAccount(rs.getString("readAccount"));
				bean.setMsg_tit(rs.getString("Msg_tit"));
				bean.setMsg_cont(rs.getString("Msg_cont"));
				bean.setMsg_date(rs.getTimestamp("Msg_date"));
				bean.setMsg_state(rs.getInt("Msg_state"));
				list.add(bean);					
			}
			
	}catch(Exception se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	
	}finally{
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}
	return list;
	}
	
	//狀態分類的總筆數
	private static final String MESSAGE_state = "SELECT count(Read_id)  from Message where msg_state=?";
	@Override
	public int getState(Integer msg_state) {
		ResultSet rest = null;
		int result = 0;
		try (Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(MESSAGE_state)){
			stmt.setInt(1, msg_state);
			rest = stmt.executeQuery();
			if (rest.next()) {
				result = rest.getInt(1);
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
	//總筆數
	private static final String MESSAGE = "SELECT count(Read_id)  from Message";
	@Override
	public int getProduct() {
		int result = 0;
		ResultSet rest = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(MESSAGE);){
	
			rest = pstmt.executeQuery();
			if (rest.next()) {
				result = rest.getInt(1);
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
	private static final String ReadCount = "SELECT count(Read_id)  from Message where Read_id=?";
	@Override
	public int getreadCount(Integer read_id) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(ReadCount);
			pstmt.setInt(1, read_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
	private static final String status = "SELECT Msg_state from Message";
	@Override
	public MessageBean select() {
		MessageBean bean = null;
		ResultSet rs = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(status);){
			rs = stmt.executeQuery();
			while(rs.next()){
				bean = new MessageBean();
				bean.setMsg_state(rs.getInt("Msg_state"));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return bean;
		}
	//依據read尋找所有頁面
	@Override
	public List<MessageBean> selectAllPage(int pageInt, Integer read_id) {
		List<MessageBean> list = null;
		ResultSet rs = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(" select m1.account as 'sendAccount', m2.Account as 'readAccount' ,Msg_id,Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state from message msg join Member m1 on msg.Send_id = m1.Member_id  join Member m2 on msg.Read_id = m2.Member_id where Read_id="+read_id+
					 " ORDER BY Msg_date desc OFFSET 5 * (" + (pageInt - 1) + ") ROWS FETCH NEXT 5 ROWS ONLY")){
			rs = stmt.executeQuery();
			list = new ArrayList<MessageBean>();
			while(rs.next()){
				MessageBean bean  = new MessageBean();
				bean.setMsg_id(rs.getInt("Msg_id"));
				bean.setSend_id(rs.getInt("Send_id"));
				bean.setRead_id(rs.getInt("Read_id"));
				bean.setSendAccount(rs.getString("sendAccount"));
				bean.setReadAccount(rs.getString("readAccount"));
				bean.setMsg_tit(rs.getString("Msg_tit"));
				bean.setMsg_cont(rs.getString("Msg_cont"));
				bean.setMsg_date(rs.getTimestamp("Msg_date"));
				bean.setMsg_state(rs.getInt("Msg_state"));
				list.add(bean);					
			}
			
	}catch(Exception se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	
	}finally{
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}
	return list;
	}



	private static final String SELECT_MESSAGE="SELECT Msg_id, Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state  From Message WHERE Msg_id=? and read_id=? order by Msg_date";
	@Override
	public MessageBean selectMessage(Integer msg_id , Integer member_id) {
		MessageBean bean = null;
		ResultSet rs = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_MESSAGE);){
			stmt.setInt(1, msg_id);
			stmt.setInt(2, member_id);
			rs = stmt.executeQuery();
			while(rs.next()){
				bean = new MessageBean();
				bean.setMsg_id(rs.getInt("Msg_id"));
				bean.setSend_id(rs.getInt("Send_id"));
				bean.setRead_id(rs.getInt("Read_id"));
				bean.setMsg_tit(rs.getString("Msg_tit"));
				bean.setMsg_cont(rs.getString("Msg_cont"));
				bean.setMsg_date(rs.getTimestamp("Msg_date"));
				bean.setMsg_state(rs.getInt("Msg_state"));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return bean;
		}



	private static final String UPDATE_STATE = "UPDATE Message set Msg_state=? where Msg_id = ?";
	@Override
	public int updateState(MessageBean bean) {
		int updateCount = 0;
		try (Connection conn = ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(UPDATE_STATE)){
			pstmt.setInt(1, bean.getMsg_state());
			pstmt.setInt(2, bean.getMsg_id());
			updateCount = pstmt.executeUpdate();
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return updateCount;
	}
}
