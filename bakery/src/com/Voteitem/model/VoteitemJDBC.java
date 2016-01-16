//package com.Voteitem.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//import com.Voteaction.model.VoteActionBean;
//
//public class VoteitemJDBC implements Voteitem{
//	private static final String URL = "jdbc:sqlserver://localhost:1433;database=bakery";
//	private static final String USERNAME = "sa";
//	private static final String PASSWORD = "sa123456";
//
//	
//	
//	private static final String INSERT="insert into Vote_item Values(?,?,?,?,?)";
//	@Override
//	public VoteitemBean insert(VoteitemBean bean) {
//		Connection conn= null;
//		PreparedStatement pstmt= null;
//		VoteitemBean result=null;
//		try {
//			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
//			pstmt=conn.prepareStatement(INSERT);
//			pstmt.setInt(1,bean.getVoteItemId());
//			pstmt.setString(2,bean.getVoteItemName());
//			pstmt.setBytes(3,bean.getVoteItemPhoto());
//			pstmt.setInt(4,bean.getVoteItemCount());
//			pstmt.setInt(5,bean.getVoteId());
//			int i=pstmt.executeUpdate();
//			if(i==0){
//				result=bean;
//			}
//			return result;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			if(pstmt!=null){
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}if(conn!=null){	
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}	
//		return result;
//	}
//
//	private static final String UPDATE="UPDATE Vote_item SET  Vote_item_name=?, "
//			+ "Vote_item_photo=?,Vote_item_count=? , Vote_id=? where Vote_item_id=?";
//	@Override
//	public VoteitemBean update(VoteitemBean itemBean) {
//		Connection conn= null;
//		PreparedStatement pstmt= null;
//		VoteitemBean result=null;
//		try {
//			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
//			pstmt=conn.prepareStatement(UPDATE);
//			pstmt.setString(1,itemBean.getVoteItemName());
//			pstmt.setBytes(2,itemBean.getVoteItemPhoto());
//			pstmt.setInt(3,itemBean.getVoteItemCount());
//			pstmt.setInt(4,itemBean.getVoteId());
//			pstmt.setInt(5,itemBean.getVoteItemId());
//			int i=pstmt.executeUpdate();
//				if(i==1){
//					result=itemBean;
//				}
//			return result;
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
//			if(conn!=null){try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//		return result;
//	}
//
//
//	@Override
//	public int delete(int Vote_item_id) {
//		
//		return 0;
//	}
//	
//	@Override
//	public List<VoteitemBean> selectAll(VoteitemBean VoteId) {
//		
//		return null;
//	}
//
//}
