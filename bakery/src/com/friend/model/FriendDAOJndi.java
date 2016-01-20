package com.friend.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	private static final String GET_MYALL_FRIENDS = 
			"select a.invite_id,a.invitee_id from friend_list a,friend_list b where a.invite_id=b.invitee_id and b.invite_id=a.invitee_id and a.invite_id=? order by invitee_id";
	@Override
	public List<FriendBean> getMyfriendsAll(Integer invite_id) {
		List<FriendBean> list = new ArrayList<FriendBean>();
		FriendBean friend_ListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MYALL_FRIENDS);
			
			pstmt.setInt(1,invite_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friend_ListVO = new FriendBean();
				friend_ListVO.setInvite_id(rs.getInt(1));
				friend_ListVO.setInvitee_id(rs.getInt(2));
				list.add(friend_ListVO);
			} 

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	
	
	
	
	
	private static final String GETMYINVITE = 
			"select invite_id,invitee_id from Friend_List where invite_id=? and not invitee_id in(select invite_id from Friend_List where invitee_id=?)";
	
	@Override
	public List<FriendBean> getMyInvite(Integer invite_id) {
		List<FriendBean> list = new ArrayList<FriendBean>();
		FriendBean friend_ListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETMYINVITE);
			
			pstmt.setInt(1,invite_id);
			pstmt.setInt(2, invite_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friend_ListVO = new FriendBean();
				friend_ListVO.setInvite_id(rs.getInt(1));
				friend_ListVO.setInvitee_id(rs.getInt(2));
				list.add(friend_ListVO);
			} 

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	
	
	
	
	
	private static final String GETWHOINVITEDME =
			"select invitee_id,invite_id from Friend_List where invitee_id=? and not invite_id in(select invitee_id from Friend_List where invite_id=?)";
	@Override
	public List<FriendBean> getWhoInvitedMe(Integer invitee_id) {
		List<FriendBean> list = new ArrayList<FriendBean>();
		FriendBean friend_ListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETWHOINVITEDME);
			
			pstmt.setInt(1, invitee_id);
			pstmt.setInt(2,invitee_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friend_ListVO = new FriendBean();
				friend_ListVO.setInvitee_id(rs.getInt(1));
				friend_ListVO.setInvite_id(rs.getInt(2));
				
				list.add(friend_ListVO);
			} // Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
