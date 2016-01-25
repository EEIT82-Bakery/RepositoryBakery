package com.article.model;

import java.text.SimpleDateFormat;
import java.util.List;


import com.rearticle.model.ReArticleBean;
import com.rearticle.model.ReArticleDAOHibernate;

public class ArticleService {
	private ArticleDAO_interface dao;
	private int recordsPerRow = 10;

	public ArticleService() {
		dao = new ArticleDAOHibernate();
	}


	public void updateArticleHidden(int articleId, int memberId, int hidden) {
		dao.updateArticleHidden(articleId, memberId, hidden);
	}
	
	public void updateArticleHidden(int articleId, int hidden) {
		dao.updateArticleHidden(articleId, hidden);
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
		return (int) (Math.ceil(dao.getRecordCounts() / (double) recordsPerRow));
	}

	// 計算總共有幾頁(articleClassNo)
	public int getTotalPages(int articleClassNo) {
		return (int) (Math.ceil(dao.getRecordCounts(articleClassNo) / (double) recordsPerRow));
	}

	// 計算總共有幾頁(articleTitle)
	public int getTotalPages(String articleTitle) {
		return (int) (Math.ceil(dao.getRecordCounts(articleTitle) / (double) recordsPerRow));
	}

	// 計算當頁資料
	public List<ArticleBean> getPageArticles(int articleClassNo, int pageNo) {
		List<ArticleBean> beans = null;
		int startRow = (pageNo - 1) * recordsPerRow;
		if (articleClassNo == 0) {
			beans = dao.getPageArticles(startRow, recordsPerRow);
		} else {
			beans = dao.getPageArticlesClass(articleClassNo, startRow, recordsPerRow);
		}
		for (ArticleBean bean : beans) {
			bean.setAuthor(bean.getMember().getAccount());
			bean.setArticleClassName(bean.getArticleClass().getArticleClassName());
			if (bean.getReArticleCount() == 0) {
				bean.setReAuthor(bean.getMember().getAccount());
				bean.setReArticleMakeDate(convertDate(bean.getArticleMake()));
			}else{
				ReArticleDAOHibernate dao = new ReArticleDAOHibernate();
				ReArticleBean reArticleBean = dao.getLastReArticle(bean.getArticleId());
				bean.setReAuthor(reArticleBean.getMember().getAccount());
				bean.setReArticleMakeDate(convertDate(reArticleBean.getReMake()));
			}
		}
		return beans;
	}

	// 針對標題搜尋文章
	public List<ArticleBean> getPageArticlesTitle(String articleTitle, int pageNo) {
		List<ArticleBean> beans = null;
		int startRow = (pageNo - 1) * recordsPerRow;
		beans = dao.getPageArticlesTitle(articleTitle, startRow, recordsPerRow);
		for (ArticleBean bean : beans) {
			bean.setAuthor(bean.getMember().getAccount());
			bean.setArticleClassName(bean.getArticleClass().getArticleClassName());
			if (bean.getReArticleCount() == 0) {
				bean.setReAuthor(bean.getMember().getAccount());
				bean.setReArticleMakeDate(convertDate(bean.getArticleMake()));
			}else{
				ReArticleDAOHibernate dao = new ReArticleDAOHibernate();
				ReArticleBean reArticleBean = dao.getLastReArticle(bean.getArticleId());
				bean.setReAuthor(reArticleBean.getMember().getAccount());
				bean.setReArticleMakeDate(convertDate(reArticleBean.getReMake()));
			}
		}
		return beans;
	}

	// 查詢一篇文章
	public ArticleBean getArticle(int articleId) {
		ArticleBean bean = dao.getOne(articleId);
		if (bean != null) {
			bean.setAuthor(bean.getMember().getAccount());
			bean.setNickName(bean.getMember().getNickname());
			bean.setArticleMakeDate(convertDate(bean.getArticleMake()));
			if(bean.getHidden() == 1){
				bean.setContent("<span style='color:#999999'>此文章已被發文者刪除</span>");
			}else if(bean.getHidden() == 2){
				bean.setContent("<span style='color:#999999'>此文章已被管理員刪除</span>");
			}
		}
		return bean; 
	}

	// 新增文章
	public void insertArticle(int memberId, int articleClassNo, String articleTitle, 
			String content) {
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

	// 編輯文章
	public void updateArticle(int articleClassNo, String articleTitle,
			String content, int articleId, int memberId) {
		dao.updateArticle(articleClassNo, articleTitle, content, articleId, memberId);
	}

	// 查詢文章標題
	public String getArticleTitle(int articleId) {
		return dao.getArticleTitle(articleId);
	}
	//會員頁面顯示他的發文
	public List<ArticleBean>getMemberArticle(int memberId){
		return dao.getMemberArticle(memberId);	
	}


}
