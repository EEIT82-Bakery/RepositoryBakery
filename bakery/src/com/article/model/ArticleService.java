package com.article.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ArticleService {
	private ArticleDAO_interface dao;
	private int recordsPerRow = 10;

	public ArticleService() {
		dao = new ArticleDAOHibernate();
	}

	//
	public void updateArticleHidden(int articleId, int memberId, int hidden) {
		dao.updateArticleHidden(articleId, memberId, hidden);
	}

	// 更新瀏覽次數
	public void updateBrowserCount(int articleId) {
		dao.updateBrowserCount(articleId);
	}

	// 檢查是否重複
	public boolean CheckArticleId(int articleId, List<Integer> articleIdList) {
		for (int temp : articleIdList) {
			if (temp == articleId) {
				return true;
			}
		}
		return false;
	}

	// 轉換日期時間
	public String convertDate(java.util.Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	// 計算總共有幾頁
	public int getTotalPages() {
		return (int) (Math.ceil(dao.getRecordCounts() / (double) recordsPerRow)); // 每頁X筆
	}
	// 計算總共有幾頁(articleClassNo)
	public int getTotalPages(int articleClassNo) {
		return (int) (Math.ceil(dao.getRecordCounts(articleClassNo) / (double) recordsPerRow)); // 每頁X筆;
	}
	// 計算總共有幾頁(articleTitle)
	public int getTotalPages(String articleTitle) {
		return (int) (Math.ceil(dao.getRecordCounts(articleTitle) / (double) recordsPerRow)); // 每頁X筆
	}

	// 計算當頁資料
	public List<ArticleBean> getPageArticles(int articleClassNo, int pageNo) {
		List<ArticleBean> beans = new ArrayList<ArticleBean>();
		List<ArticleBean> temp = null;
		int startRow = (pageNo - 1) * recordsPerRow;
		if (articleClassNo == 0) {
			temp = dao.getPageArticles(startRow, recordsPerRow);
		} else {
			temp = dao.getPageArticlesClass(articleClassNo, startRow, recordsPerRow);
		}
		for (ArticleBean bean : temp) {
			bean.setArticleClassName(bean.getArticleClass().getArticleClassName());
			bean.setReArticleMakeDate(new ArticleService().convertDate(bean.getReArticleMake()));
			beans.add(bean);
		}
		return beans;
	}

	// 針對標題搜尋文章
	public List<ArticleBean> getPageArticlesTitle(String articleTitle, int pageNo) {
		List<ArticleBean> beans = new ArrayList<ArticleBean>();
		List<ArticleBean> temp = null;
		int startRow = (pageNo - 1) * recordsPerRow;
		temp = dao.getPageArticlesTitle(articleTitle, startRow, recordsPerRow);
		for (ArticleBean bean : temp) {
			bean.setArticleClassName(bean.getArticleClass().getArticleClassName());
			bean.setReArticleMakeDate(new ArticleService().convertDate(bean.getReArticleMake()));
			beans.add(bean);
		}
		return beans;
	}

	// 查詢一篇文章
	public ArticleBean getArticle(int articleId) {
		return dao.getOne(articleId);
	}

	// 新增文章
	public void insertArticle(int memberId, int articleClassNo, String articleTitle, String content) {
		ArticleBean bean = new ArticleBean();
		bean.setMemberId(memberId);
		bean.setArticleClassNo(articleClassNo);
		bean.setArticleTitle(articleTitle);
		bean.setContent(content);
		bean.setReArticleCount(0);
		bean.setBrowserCount(0);
		bean.setArticleMake(new java.util.Date());
		bean.setReArticleMake(new java.util.Date());
		bean.setHidden(0);
		dao.insertArticle(bean);
	}

	// 查詢文章種類
	public List<ArticleClassBean> getArticleClass() {
		return dao.getArticleClass();
	}

	// 編輯文章
	public boolean updateArticle(int articleClassNo, String articleTitle, String content, int articleId, int memberId) {
		dao.updateArticle(articleClassNo, articleTitle, content, articleId, memberId);
		return true;
	}

	// 查詢文章標題
	public String getArticleTitle(int articleId) {
		return dao.getArticleTitle(articleId);
	}

}
