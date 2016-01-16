<<<<<<< HEAD
package com.Voteaction.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class VoteActionJNDI implements VoteAction{
		
		private DataSource ds=null;
	
	public VoteActionJNDI(){
				try {
					Context ctx	=new InitialContext();
					ds=(DataSource)ctx.lookup("java:comp/env/jdbc/cake");
				} catch (NamingException e) {
					e.printStackTrace();
				}	
	}

	private static final String INSERT="insert into Vote_Action Values(?,?,?,?,?)";
	@Override
	public VoteActionBean insert(VoteActionBean bean) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		VoteActionBean result= null;
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(INSERT);
			if(bean!=null){
				pstmt.setInt(1, bean.getVoteId());	
				pstmt.setString(2,bean.getVoteTitle());
				pstmt.setString(3,bean.getVoteDescribe());
				pstmt.setDate(4,(java.sql.Date)bean.getVoteStartDate());
				pstmt.setDate(5,(java.sql.Date)bean.getVoteEndDate());
				int i=pstmt.executeUpdate();
				if(i==1){
					result=bean;
					return result; 
				}
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}	finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	private static final String UPDATE="UPDATE Vote_action SET Vote_title=?,Vote_describe=?,Vote_start_date=?,Vote_end_date=? where Vote_id=?";
	@Override
	public int update(String title, String describe, Date start, Date end, int voteid) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int utdateCount=0;
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(UPDATE);
			pstmt.setString(1,title);
			pstmt.setString(2,describe);
			pstmt.setDate(3,new java.sql.Date(start.getTime()));
			pstmt.setDate(4,new java.sql.Date(end.getTime()) );
			pstmt.setInt(5,voteid);
			utdateCount=pstmt.executeUpdate();
			return utdateCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return utdateCount;
	}

	private static final String DELETE="DELETE Vote_action where Vote_id=?";
	@Override
	public int delete(int VoteId) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int utdateCount=0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, VoteId);
			utdateCount = pstmt.executeUpdate();
			return utdateCount;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		return utdateCount;
	}
	private static final String SELECTALL="SELECT * FROM Vote_action";
	@Override
	public List<VoteActionBean> selectall() {
		VoteActionBean vote=null;
		Connection conn= null;
		PreparedStatement pstmt= null;
		List<VoteActionBean> votes= new ArrayList<VoteActionBean>();
			try {
				conn = ds.getConnection();
				pstmt=conn.prepareStatement(SELECTALL);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()){
					vote=new VoteActionBean();
					vote.setVoteId(rs.getInt("Vote_id"));
					vote.setVoteTitle(rs.getString("Vote_title"));
					vote.setVoteDescribe(rs.getString("Vote_describe"));
					vote.setVoteStartDate(rs.getDate("Vote_start_date"));
					vote.setVoteEndDate(rs.getDate("Vote_end_date"));
					votes.add(vote);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(pstmt!=null){
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			}	
			return votes;
	}

	@Override
	public VoteActionBean selectPk(int VoteId) {
		
		return null;
	}
	
	

}
=======
//package com.Voteaction.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class VoteActionJNDI implements VoteAction_Interface{
//		
//		private DataSource ds=null;
//	
//	public VoteActionJNDI(){
//				try {
//					Context ctx	=new InitialContext();
//					ds=(DataSource)ctx.lookup("java:comp/env/jdbc/cake");
//				} catch (NamingException e) {
//					e.printStackTrace();
//				}	
//	}
//
//	private static final String INSERT="insert into Vote_Action Values(?,?,?,?,?)";
//	@Override
//	public VoteActionBean insert(VoteActionBean bean) {
//		Connection conn= null;
//		PreparedStatement pstmt= null;
//		VoteActionBean result= null;
//		try {
//			conn=ds.getConnection();
//			pstmt=conn.prepareStatement(INSERT);
//			if(bean!=null){
//				pstmt.setInt(1, bean.getVoteId());	
//				pstmt.setString(2,bean.getVoteTitle());
//				pstmt.setString(3,bean.getVoteDescribe());
//				pstmt.setDate(4,(java.sql.Date)bean.getVoteStartDate());
//				pstmt.setDate(5,(java.sql.Date)bean.getVoteEndDate());
//				int i=pstmt.executeUpdate();
//				if(i==1){
//					result=bean;
//					return result; 
//				}
//			}
//		} catch (SQLException e) {	
//			e.printStackTrace();
//		}	finally{
//			if(pstmt!=null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(conn!=null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return result;
//	}
//	private static final String UPDATE="UPDATE Vote_action SET Vote_title=?,Vote_describe=?,Vote_start_date=?,Vote_end_date=? where Vote_id=?";
//	@Override
//	public int update(String title, String describe, Date start, Date end, int voteid) {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		int utdateCount=0;
//		try {
//			conn=ds.getConnection();
//			pstmt=conn.prepareStatement(UPDATE);
//			pstmt.setString(1,title);
//			pstmt.setString(2,describe);
//			pstmt.setDate(3,new java.sql.Date(start.getTime()));
//			pstmt.setDate(4,new java.sql.Date(end.getTime()) );
//			pstmt.setInt(5,voteid);
//			utdateCount=pstmt.executeUpdate();
//			return utdateCount;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			if(pstmt!=null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(conn!=null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return utdateCount;
//	}
//
//	private static final String DELETE="DELETE Vote_action where Vote_id=?";
//	@Override
//	public int delete(int VoteId) {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		int utdateCount=0;
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(DELETE);
//			pstmt.setInt(1, VoteId);
//			utdateCount = pstmt.executeUpdate();
//			return utdateCount;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			if(pstmt!=null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(conn!=null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}	
//		}
//		return utdateCount;
//	}
//	private static final String SELECTALL="SELECT * FROM Vote_action";
//	@Override
//	public List<VoteActionBean> selectall() {
//		VoteActionBean vote=null;
//		Connection conn= null;
//		PreparedStatement pstmt= null;
//		List<VoteActionBean> votes= new ArrayList<VoteActionBean>();
//			try {
//				conn = ds.getConnection();
//				pstmt=conn.prepareStatement(SELECTALL);
//				ResultSet rs=pstmt.executeQuery();
//				while(rs.next()){
//					vote=new VoteActionBean();
//					vote.setVoteId(rs.getInt("Vote_id"));
//					vote.setVoteTitle(rs.getString("Vote_title"));
//					vote.setVoteDescribe(rs.getString("Vote_describe"));
//					vote.setVoteStartDate(rs.getDate("Vote_start_date"));
//					vote.setVoteEndDate(rs.getDate("Vote_end_date"));
//					votes.add(vote);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally{
//				if(pstmt!=null){
//					try {
//						pstmt.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//				if(conn!=null){
//					try {
//						conn.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}	
//			}	
//			return votes;
//	}
//
//	@Override
//	public VoteActionBean selectPk(int VoteId) {
//		
//		return null;
//	}
//
//	@Override
//	public void insert(VoteActionBean bean) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void update(String title, String describe, Date end, int voteStatus, int voteid) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(int voteId) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//
//}
>>>>>>> branch 'master' of https://github.com/EEIT82-Bakery/RepositoryBakery.git
