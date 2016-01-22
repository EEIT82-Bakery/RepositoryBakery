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

import com.message.model.MessageBean;

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
	private static final String INSERTFRIEND = "insert into friend_list(invite_id,invitee_id,friendstatu) values (?,?,?)";
	
	@Override
	public void insert(FriendBean bean) {	
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERTFRIEND);){		
			conn.setAutoCommit(false);
			stmt.setInt(1, bean.getInvite_id());
			stmt.setInt(2,bean.getInvitee_id());
			stmt.setInt(3, bean.getFriendstatu());
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
			"select invitee_id,invite_id from Friend_List where invitee_id=? and friendstatu";
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
	private static final String UPDATE = "UPDATE Friend_List set friendstatu=? where invite_id=? and invitee_id=?";
	@Override
	public int update(FriendBean bean) {
		int updateCount = 0;
		try (Connection conn = ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(UPDATE)){
			pstmt.setInt(1, bean.getFriendstatu());
			pstmt.setInt(2, bean.getInvite_id());
			pstmt.setInt(3, bean.getInvitee_id());	
			updateCount = pstmt.executeUpdate();
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());	
		}
		return updateCount;
	}	
	private static final String DELETE = "DELETE FROM Friend_List where invite_id=? and invitee_id=? ";
	@Override
	public void delete(Integer invite_id, Integer invitee_id) {
		try (Connection conn = ds.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(DELETE)){
			pstmt.setInt(1, invite_id);
			pstmt.setInt(2, invitee_id);
			pstmt.executeUpdate();
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		}
	
	}
	private static final String SELECT_FRIEND_MAIL = "SELECT friendstatu from Friend_List where invite_id=? and invitee_id=?";
	@Override
	public int select(Integer invite_id, Integer invitee_id) {
		int statu = -1;
		ResultSet rset = null;
		try(Connection conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_FRIEND_MAIL)){
			stmt.setInt(1, invite_id);
			stmt.setInt(2, invitee_id);
			rset = stmt.executeQuery();
			if(rset.next()){
				statu = rset.getInt("friendstatu");
			}	
		}catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return	statu;
	}
	private static final String CHRCKFRIEND = "SELECT invitee_id, friendstatu from friend_List where invite_id=?";
	@Override
	public List<FriendBean> selectIsFriend(Integer invite_id) {
		List<FriendBean> list = null;
		FriendBean bean = null;
		ResultSet rset = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(CHRCKFRIEND);){
			stmt.setInt(1, invite_id);
			rset = stmt.executeQuery();
			list = new ArrayList<FriendBean>();
			while(rset.next()){				
				bean = new FriendBean();
				bean.setInvitee_id(rset.getInt("invitee_id"));
				bean.setFriendstatu(rset.getInt("friendstatu"));
				list.add(bean);		
			}			
		}catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return	list;
	}
	
	
	
	
	
	@Override
	public FriendBean selec(Integer invite_id, Integer invitee_id) {
		FriendBean bean = null;
		ResultSet rset = null;
		try(Connection conn=ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_FRIEND_MAIL)){
			stmt.setInt(1, invite_id);
			stmt.setInt(2, invitee_id);
			rset = stmt.executeQuery();
			if(rset.next()){
				bean = new FriendBean();
				bean.setFriendstatu(rset.getInt("friendstatu"));
			}	
		}catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
				if (rset != null) {
					try {
						rset.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}
		return	bean;
	}	
	
	
	
	
	private static final String REAL_DELETE = "DELETE FROM Friend_List where invite_id=? and invitee_id=? ";
	@Override
	public void RealDelete(Integer invite_id, Integer invitee_id) {
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(REAL_DELETE)){
				pstmt.setInt(1, invite_id);
				pstmt.setInt(2, invitee_id);
				pstmt.executeUpdate();
				pstmt.setInt(1, invitee_id);
				pstmt.setInt(2, invite_id);
				pstmt.executeUpdate();			
				conn.commit();
				
			} catch (Exception se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());				
		}
	}
	
	
	
	//**自己的好友分頁面
	@Override
	public List<FriendBean> selectAllPage(int pageInt, Integer invite_id) {
		List<FriendBean> lists = null;
		ResultSet rest = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(" select m1.account as 'inviteAccount',m1.Picture as 'invitePiture', m2.Account as 'inviteeAccount' ,m2.Picture as 'inviteePicture' ,invite_id , invitee_id,friendstatu from friend_list fdl join Member m1 on fdl.invite_id = m1.Member_id  join Member m2 on fdl.invitee_id = m2.Member_id where invite_id="+invite_id
					+ " ORDER BY friendstatu OFFSET 8 * (" + (pageInt - 1) + ") ROWS FETCH NEXT 8 ROWS ONLY");){
			
			rest = stmt.executeQuery();
			lists = new ArrayList<FriendBean>();
			while(rest.next()){
				FriendBean bean = new FriendBean();
				bean.setInvite_id(rest.getInt("invite_id"));
				bean.setInvitee_id(rest.getInt("invitee_id"));
				bean.setFriendstatu(rest.getInt("friendstatu"));
				bean.setInviteAccount(rest.getString("inviteAccount"));
				bean.setInviteeAccount(rest.getString("inviteeAccount"));
				bean.setInvitePiture(rest.getBytes("invitePiture"));
				bean.setInviteePicture(rest.getBytes("inviteePicture"));
				lists.add(bean);
			}	
		}catch(Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return lists;
		
	}
	//**全部好友總數
	private static final String ALLFRIENDCOUNT="SELECT count(invitee_id) FROM friend_list where invite_id=?";
	@Override
	public int allFriendCount(Integer invite_id) {
		int count = 0;
		ResultSet rest = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(ALLFRIENDCOUNT)){
			stmt.setInt(1, invite_id);
			rest = stmt.executeQuery();
			while(rest.next()){
				count = rest.getInt(1);
			}	
		}catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());				
		}finally{
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return count;
	}

	//***狀態總數
	private static final String STATUFRIENDCOUNT="SELECT count(invitee_id) FROM friend_list where invite_id=? and friendstatu=?";
	@Override
	public int CheckCount(Integer invite_id, Integer friendstatu) {
		int count = 0;
		ResultSet rest = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(STATUFRIENDCOUNT)){
			stmt.setInt(1, invite_id);
			stmt.setInt(2, friendstatu);
			rest = stmt.executeQuery();
			while(rest.next()){
				count = rest.getInt(1);
			}
			
		}catch (Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());				
		}finally{
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return count;
	}
	
	
	//**依照是不是好友找尋出來
	@Override
	public List<FriendBean> selectPage(int pageInt, Integer invite_id,
			Integer friendstatu) {
		List<FriendBean> lists = null;
		ResultSet rest = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(" select m1.account as 'inviteAccount', m2.Account as 'inviteeAccount' ,invite_id , invitee_id,friendstatu, from friend_list fdl join Member m1 on fdl.invite_id = m1.Member_id  join Member m2 on fdl.invitee_id = m2.Member_id where invite_id="+invite_id+"and friendstatu="+friendstatu
					+ " ORDER BY friendstatu OFFSET 5 * (" + (pageInt - 1) + ") ROWS FETCH NEXT 5 ROWS ONLY");){
			
			rest = stmt.executeQuery();
			lists = new ArrayList<FriendBean>();
			while(rest.next()){
				FriendBean bean = new FriendBean();
				bean.setInvite_id(rest.getInt("invited_id"));
				bean.setInvitee_id(rest.getInt("invitee_id"));
				bean.setFriendstatu(rest.getInt("friendstatu"));
				bean.setInviteAccount(rest.getString("inviteAccount"));
				bean.setInviteeAccount(rest.getString("inviteeAccount"));	
				lists.add(bean);
			}	
		}catch(Exception se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (rest != null) {
				try {
					rest.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return lists;
		
	}
	
	
	
	
	
	
}
