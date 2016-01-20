package com.rearticle.model;

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

public class ReArticleDAOJNDI implements ReArticleDAO_interface {

	private DataSource ds = null;

	public ReArticleDAOJNDI() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增回文
	private final static String FIND_MAX_REID = "select MAX(re_id) as re_id from Re_Article where article_id = ?";
	private final static String INSERT_REARTICLE = "insert into re_article (re_id,member_id,re_content,re_make,hidden,article_id) values (?,?,?,getdate(),0,?)";

	@Override
	public int insertReArticle(ReArticleBean bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int reId = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_MAX_REID);
			pstmt.setInt(1, bean.getArticleId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reId = rs.getInt("re_id");
				reId = reId + 1;
			} else {
				reId = 1;
			}
			pstmt = conn.prepareStatement(INSERT_REARTICLE);
			pstmt.setInt(1, reId);
			pstmt.setInt(2, bean.getMemberId());
			pstmt.setString(3, bean.getReContent());
			pstmt.setInt(4, bean.getArticleId());
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
		return reId;
	}

	@Override
	public int delete(int reArticleId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	private final static String UPDATE_RE_ARTICLE = "update re_article set re_content = ? where article_id = ? and re_id = ? and member_id = ?";

	@Override
	public void updateReArticle(String reContent, int reId, int articleId, int memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_RE_ARTICLE);
			pstmt.setString(1, reContent);
			pstmt.setInt(2, articleId);
			pstmt.setInt(3, reId);
			pstmt.setInt(4, memberId);
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

	// 取得全部回文
	private static final String GET_ALL_REARTICLE = "select re_id , m.member_id ,nickname , Account ,"
			+ " Picture , re_content , re_make , hidden from Re_Article re join Member m "
			+ "on re.member_id = m.Member_id where article_id = ?";

	@Override
	public List<ReArticleBean> getAllReArticle(int articleId) {
		List<ReArticleBean> beans = new ArrayList<ReArticleBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_REARTICLE);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReArticleBean bean = new ReArticleBean();
				bean.setReId(rs.getInt("re_id"));
				bean.setMemberId(rs.getInt("member_id"));
				bean.setNickName(rs.getString("nickname"));
				bean.setAccount(rs.getString("account"));
				bean.setPictureTemp(rs.getBytes("picture"));
				bean.setReContent(rs.getString("re_content"));
				bean.setReMake(new java.util.Date(rs.getTimestamp("re_make").getTime()));
				bean.setHidden(rs.getInt("hidden"));
				beans.add(bean);
			}
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

		return beans;
	}

	// 尋找最後一筆回文
	private final static String FIND_LAST_REARTICLE = "select top(1) Account, re_make "
			+ "from Re_Article re join Member m on re.member_id = m.Member_id "
			+ "where article_id = ? order by re_make desc";

	@Override
	public ReArticleBean getLastReArticle(int articleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReArticleBean bean = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_LAST_REARTICLE);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new ReArticleBean();
				bean.setAccount(rs.getString("account"));
				bean.setReMake(new java.util.Date(rs.getTimestamp("re_make").getTime()));
			}
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
		return bean;
	}

	private static final String GET_ONE_RE_ARTICLE = "select re.member_id , re_id , article_title , re_content , hidden from Re_Article re join Article a on re.article_id = a.article_id  where a.article_id = ? and re_id = ?";

	@Override
	public ReArticleBean getOneReArticle(int articleId, int reId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReArticleBean bean = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_RE_ARTICLE);
			pstmt.setInt(1, articleId);
			pstmt.setInt(2, reId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new ReArticleBean();
				bean.setMemberId(rs.getInt("member_id"));
				bean.setReId(rs.getInt("re_Id"));
				bean.setArticleTitle(rs.getString("article_title"));
				bean.setReContent(rs.getString("re_content"));
				bean.setHidden(rs.getInt("hidden"));
			}
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
		return bean;
	}
	
	private final static String UPDATE_RE_ARTICLE_HIDDEN = "update re_article set hidden = ? where article_id = ? and re_id = ? and member_id = ?";

	@Override
	public void updateReArticleHidden(int articleId, int reId, int memberId, int hidden) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_RE_ARTICLE_HIDDEN);
			pstmt.setInt(1, hidden);
			pstmt.setInt(2, articleId);
			pstmt.setInt(3, reId);
			pstmt.setInt(4, memberId);
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


	@Override
	public void updateReArticleHidden(int Id, int hidden) {
		// TODO Auto-generated method stub
		
	}
}
