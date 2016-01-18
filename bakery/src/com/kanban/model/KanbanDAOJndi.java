package com.kanban.model;

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





public class KanbanDAOJndi implements KanbanDAO_Interface {
	
	
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String SELECT = "SELECT * FROM KANBAN WHERE KANBANID = ?";
	private static final String INSERT = "INSERT INTO KANBAN(Member_id,Title,Detail,Photo)values(?,?,?,?)";
	private static final String DELETE = "Delete from Kanban where kanbanid=?";
	private static final String UDPDATE= "Update Kanban set title=? , Detail=? ,Photo=? where member_id=?";
	private static final String SELECT_ID = "SELECT kanbanid,Member_id,title,Detail,Photo from Kanban where member_id=?";

	@Override
	public List<KanbanBean> select(int kanbanid) {
		List<KanbanBean> beans = new ArrayList<>();
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT);
			stmt.setInt(1, kanbanid);
			rset = stmt.executeQuery();
			while (rset.next()) {
				KanbanBean bean = new KanbanBean();
				bean.setKanbanid(rset.getInt("Kanbanid"));
				bean.setMemberid(rset.getInt("Member_id"));
				bean.setDetail(rset.getString("Detail"));
				bean.setPhoto(rset.getBytes("photo"));
				bean.setTitle(rset.getString("Title"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					}
				}
			}
		return beans;
	}

	
	@Override
	public void insert(KanbanBean bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, bean.getMemberid());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getDetail());
			pstmt.setBytes(4, bean.getPhoto());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
		

	@Override
	public void delete(int kanbanid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, kanbanid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
	}

	@Override
	public KanbanBean update(KanbanBean bean) {
		KanbanBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(UDPDATE);
			stmt.setString(1, bean.getTitle());
			stmt.setString(2, bean.getDetail());
			stmt.setBytes(3, bean.getPhoto());
			stmt.setInt(4, bean.getMemberid());
			int i = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
	public List<KanbanBean> selectId(int memberid) {
		List<KanbanBean> beans = new ArrayList<>();
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_ID);
			stmt.setInt(1, memberid);
			rset = stmt.executeQuery();
			while (rset.next()) {
				KanbanBean bean = new KanbanBean();
				bean.setKanbanid(rset.getInt("Kanbanid"));
				bean.setMemberid(rset.getInt("Member_id"));
				bean.setDetail(rset.getString("Detail"));
				bean.setPhoto(rset.getBytes("photo"));
				bean.setTitle(rset.getString("Title"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					}
				}
			}
		return beans;
	}
}
