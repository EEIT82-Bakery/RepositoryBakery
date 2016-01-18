package com.friend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FriendDAOJndi implements FriendDAO {
	
	private DataSource ds = null;
	public FriendDAOJndi() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERTFRIEND = "insert into friend_list(invite_id,invitee_id) values (?,?)";
	
	@Override
	public void insert(FriendBean bean) {
		
		
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERTFRIEND);){
			
			conn.setAutoCommit(false);
			stmt.setInt(1, bean.getInvite_id());
			stmt.setInt(2,bean.getInvitee_id());
			stmt.executeUpdate();
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
			
		
	}

}
