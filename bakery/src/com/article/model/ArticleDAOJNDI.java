package com.article.model;

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

import com.rearticle.model.ReArticleBean;
import com.rearticle.model.ReArticleDAOJNDI;

public class ArticleDAOJNDI implements ArticleDAO_interface {

	private DataSource ds = null;

	public ArticleDAOJNDI() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_ARTICLE = "insert into Article(member_id, "
			+ "article_class_no, article_title, content, re_article_count, browser_count, "
			+ "article_make, re_article_date, hidden) values (?,?,?,?,0,0,GETDATE(),GETDATE(),0)";
	private static final String FIND_NEW_ARTICLE = "select MAX(article_id) as article_id from article where member_id = ?";

//	// 新增文章
//	@Override
//	public int insertArticle(int memberId, int articleClassNo, String articleTitle, String content) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int articleId = 0;
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(INSERT_ARTICLE);
//			pstmt.setInt(1, memberId);
//			pstmt.setInt(2, articleClassNo);
//			pstmt.setString(3, articleTitle);
//			pstmt.setString(4, content);
//			int i = pstmt.executeUpdate();
//			if (i == 1) {
//				pstmt = conn.prepareStatement(FIND_NEW_ARTICLE);
//				pstmt.setInt(1, memberId);
//				rs = pstmt.executeQuery();
//				if (rs.next()) {
//					articleId = rs.getInt("article_id");
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return articleId;
//	}

	// 刪除
	private static final String DELETE = "delete from Article where article_id = ?";

	@Override
	public int delete(int articleId) {
		return 0;
	}

	// 編輯回文
	private final static String UPDATE_ARTICLE = "update article set article_class_no = ? , article_title = ? , content = ? where article_id = ? and member_id = ?";

	@Override
	public boolean updateArticle(int articleClassNo, String articleTitle, String content, int articleId, int memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_ARTICLE);
			pstmt.setInt(1, articleClassNo);
			pstmt.setString(2, articleTitle);
			pstmt.setString(3, content);
			pstmt.setInt(4, articleId);
			pstmt.setInt(5, memberId);
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
		return true;
	}

	// 更新回文次數跟時間
	private final static String UPDATE_RE_ARTICLE_COUNT = "update Article set re_article_count = ?, re_article_date = ? where article_id = ?";

	@Override
	public void updateReArticleCount(int reId, int articleId, ReArticleBean bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_RE_ARTICLE_COUNT);
			pstmt.setInt(1, reId);
			pstmt.setTimestamp(2, new java.sql.Timestamp(bean.getReMake().getTime()));
			pstmt.setInt(3, articleId);
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

	// 查詢一筆
	private static final String GET_ONE = "select article_id ,m.member_id , article_class_no , nickname , Account , Picture ,"
			+ "article_title , content , article_make , hidden from Article a join Member m "
			+ "on a.member_id = m.Member_id where article_id = ?";

	@Override
	public ArticleBean getOne(int articleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArticleBean bean = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ONE);
			pstmt.setInt(1, articleId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ArticleBean();
				bean.setArticleId(rs.getInt("article_Id"));
				bean.setMemberId(rs.getInt("member_Id"));
				bean.setAuthor(rs.getString("account"));
				bean.setNickName(rs.getString("nickname"));
				bean.setPictureTemp(rs.getBytes("picture"));
				bean.setArticleClassNo(rs.getInt("article_class_no"));
				bean.setArticleTitle(rs.getString("article_Title"));
				bean.setContent(rs.getString("content"));
				bean.setArticleMake(new java.util.Date(rs.getTimestamp("article_Make").getTime()));
				bean.setHidden(rs.getInt("hidden"));
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
		}
		return bean;
	}

	// 計算紀錄總筆數
	private static final String COUNT_ROW = "SELECT count(*) FROM Article";

	@Override
	public int getRecordCounts() {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(COUNT_ROW);
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

	// 針對"文章種類"計算紀錄總筆數
	private static final String COUNT_ROW_CLASS = "SELECT count(*) FROM Article where article_class_no = ?";

	@Override
	public int getRecordCounts(int articleClassNo) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(COUNT_ROW_CLASS);
			pstmt.setInt(1, articleClassNo);
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

	// 針對"文章標題"計算紀錄總筆數
	private static final String COUNT_ROW_TITLE = "SELECT count(*) FROM Article where article_Title like ?";

	@Override
	public int getRecordCounts(String articleTitle) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(COUNT_ROW_TITLE);
			pstmt.setString(1, "%" + articleTitle + "%");
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

	// 讀取某一頁的所有紀錄
	private static final String GET_PAGE_ROW = "select article_id,Account,a.article_class_no,"
			+ "article_class_name,article_title,re_article_count,browser_count,article_make,"
			+ "re_article_date , hidden from article a join article_class_name ac "
			+ "on a.article_class_no = ac.article_class_no join Member m "
			+ "on a.member_id = m.Member_id Order by re_article_date desc Offset ? rows " + "Fetch Next ? Rows Only";

	@Override
	public List<ArticleBean> getPageArticles(int startRow, int recordsPerRow) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ArticleBean> beans = new ArrayList<ArticleBean>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_PAGE_ROW);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, recordsPerRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleBean bean = new ArticleBean();
				bean.setArticleId(rs.getInt("article_id"));
				bean.setAuthor(rs.getString("account"));
				bean.setArticleClassNo(rs.getInt("article_class_no"));
				bean.setArticleClassName(rs.getString("article_class_name"));
				bean.setArticleTitle(rs.getString("article_Title"));
				bean.setReArticleCount(rs.getInt("re_Article_Count"));
				bean.setBrowserCount(rs.getInt("browser_Count"));
				if (rs.getInt("re_Article_Count") == 0) {
					bean.setReAuthor(rs.getString("account"));
					bean.setReArticleMake(new java.util.Date(rs.getTimestamp("article_Make").getTime()));
				} else {
					ReArticleDAOJNDI dao = new ReArticleDAOJNDI();
					ReArticleBean reArticleBean = dao.getLastReArticle(rs.getInt("article_id"));
					bean.setReAuthor(reArticleBean.getAccount());
					bean.setReArticleMake(reArticleBean.getReMake());
				}
				bean.setHidden(rs.getInt("hidden"));
				beans.add(bean);
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
		return beans;
	}

	// 針對"文章種類"讀取某一頁的所有紀錄
	private static final String GET_PAGE_CLASS = "select article_id,Account,a.article_class_no,"
			+ "article_class_name,article_title,re_article_count,browser_count,article_make,"
			+ "re_article_date , hidden from article a join article_class_name ac "
			+ "on a.article_class_no = ac.article_class_no join Member m "
			+ "on a.member_id = m.Member_id where a.article_class_no = ?  "
			+ "Order by re_article_date desc Offset ? rows Fetch Next ? Rows Only";

	@Override
	public List<ArticleBean> getPageArticlesClass(int articleClassNo, int startRow, int recordsPerRow) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ArticleBean> beans = new ArrayList<ArticleBean>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_PAGE_CLASS);
			conn.setAutoCommit(false);
			pstmt.setInt(1, articleClassNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, recordsPerRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleBean bean = new ArticleBean();
				bean.setArticleId(rs.getInt("article_id"));
				bean.setAuthor(rs.getString("account"));
				bean.setArticleClassNo(rs.getInt("article_class_no"));
				bean.setArticleClassName(rs.getString("article_class_name"));
				bean.setArticleTitle(rs.getString("article_Title"));
				bean.setReArticleCount(rs.getInt("re_Article_Count"));
				bean.setBrowserCount(rs.getInt("browser_Count"));
				if (rs.getInt("re_Article_Count") == 0) {
					bean.setReAuthor(rs.getString("account"));
					bean.setReArticleMake(new java.util.Date(rs.getTimestamp("article_Make").getTime()));
				} else {
					ReArticleDAOJNDI dao = new ReArticleDAOJNDI();
					ReArticleBean reArticleBean = dao.getLastReArticle(rs.getInt("article_id"));
					bean.setReAuthor(reArticleBean.getAccount());
					bean.setReArticleMake(reArticleBean.getReMake());
				}
				bean.setHidden(rs.getInt("hidden"));
				beans.add(bean);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		return beans;
	}

	// 標題搜尋所有文章
	private static final String GET_PAGE_TITTLE = "select article_id,Account,a.article_class_no,"
			+ "article_class_name,article_title,re_article_count,browser_count,"
			+ "article_make,re_article_date,hidden from article a join article_class_name ac "
			+ "on a.article_class_no = ac.article_class_no join Member m "
			+ "on a.member_id = m.Member_id where a.article_title like ? "
			+ "Order by re_article_date desc Offset ? rows Fetch Next ? Rows Only";

	@Override
	public List<ArticleBean> getPageArticlesTitle(String articleTitle , int startRow, int recordsPerRow) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ArticleBean> beans = new ArrayList<ArticleBean>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_PAGE_TITTLE);
			conn.setAutoCommit(false);
			pstmt.setString(1, "%" + articleTitle + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, recordsPerRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleBean bean = new ArticleBean();
				bean.setArticleId(rs.getInt("article_id"));
				bean.setAuthor(rs.getString("account"));
				bean.setArticleClassNo(rs.getInt("article_class_no"));
				bean.setArticleClassName(rs.getString("article_class_name"));
				bean.setArticleTitle(rs.getString("article_Title"));
				bean.setReArticleCount(rs.getInt("re_Article_Count"));
				bean.setBrowserCount(rs.getInt("browser_Count"));
				if (rs.getInt("re_Article_Count") == 0) {
					bean.setReAuthor(rs.getString("account"));
					bean.setReArticleMake(new java.util.Date(rs.getTimestamp("article_Make").getTime()));
				} else {
					ReArticleDAOJNDI dao = new ReArticleDAOJNDI();
					ReArticleBean reArticleBean = dao.getLastReArticle(rs.getInt("article_id"));
					bean.setReAuthor(reArticleBean.getAccount());
					bean.setReArticleMake(reArticleBean.getReMake());
				}
				bean.setHidden(rs.getInt("hidden"));
				beans.add(bean);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		return beans;
	}

	// 查詢文章種類
	private static final String GET_ARTICLE_CLASS = "select article_class_no , article_class_name from article_class_name";

	@Override
	public List<ArticleClassBean> getArticleClass() {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ArticleClassBean> beans = new ArrayList<ArticleClassBean>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ARTICLE_CLASS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleClassBean bean = new ArticleClassBean();
				bean.setArticleClassNo(rs.getInt("article_class_no"));
				bean.setArticleClassName(rs.getString("article_class_name"));
				beans.add(bean);
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
		return beans;
	}

	private final static String GET_ARTICLE_TITLE = "select article_title from article where article_Id = ?";

	@Override
	public String getArticleTitle(int articleId) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String articleTitle = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ARTICLE_TITLE);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleTitle = rs.getString("article_title");
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
		return articleTitle;
	}

	private final static String GET_BROWSER_COUNT = "select browser_count from Article where article_id = ?";

	@Override
	public int getBrowserCount(int articleId) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int browserCount = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_BROWSER_COUNT);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				browserCount = rs.getInt("browser_count");
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
		return browserCount;
	}

	private final static String UPDATE_BROWSER_COUNT = "update article set browser_count = ? where article_id = ?";

	@Override
	public void updateBrowserCount(int browserCount, int articleId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_BROWSER_COUNT);
			pstmt.setInt(1, browserCount);
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

	private final static String UPDATE_ARTICLE_HIDDEN = "update article set hidden = ? where article_Id = ? and member_Id= ?";

	@Override
	public void updateArticleHidden(int articleId , int memberId , int hidden) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE_ARTICLE_HIDDEN);
			pstmt.setInt(1, hidden);
			pstmt.setInt(2, articleId);
			pstmt.setInt(3, memberId);
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
	public int insertArticle(ArticleBean bean) {
		// TODO Auto-generated method stub
		return 0;
	}
}
