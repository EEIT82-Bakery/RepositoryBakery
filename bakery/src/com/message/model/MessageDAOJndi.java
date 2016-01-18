package com.message.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	
	
	private static final String SELECT="SELECT Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state From Message where send_id=?,read_id=?,msg_date=?";
	
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
			
			

	private static final String INSERT = "INSERT INTO Message (Send_id,Read_id,Msg_tit,Msg_cont,Msg_date,Msg_state) values=(?,?,?,?,?,?)";
	
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


	
	private static final 
	@Override
	public int delete(Integer send_id, Integer read_id, Date msg_date) {
	
		return 0;
	}

	@Override
	public MessageBean update(MessageBean bean) {
		
		return null;
	}

	@Override
	public List<MessageBean> selectAll() {

		return null;
	}

	@Override
	public List<MessageBean> getgivemymsg(Integer rece_id, Integer msg_state) {
		
		return null;
	}

}
