package com.articlecollection.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArticleCollectionDAOJNDI implements ArticleCollectionDAO_interface {
	private DataSource ds = null;

	public ArticleCollectionDAOJNDI() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private final static String ADD_ARTICLE_COLLECTION = "insert into article_collection (member_id , article_id) values (? , ?)";

	@Override
	public void addCollection(int memberId, int articleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(ADD_ARTICLE_COLLECTION);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, articleId);
			pstmt.executeUpdate();
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
	}
	private final static String GET_HAVE_COLLECTION = "select article_id from article_collection where member_id = ? and article_id = ?";
	
	@Override
	public boolean getHaveCollection(int memberId , int articleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_HAVE_COLLECTION);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, articleId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
