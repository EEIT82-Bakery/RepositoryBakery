package com.articlereport.model;

import java.util.List;

import com.article.model.ArticleService;

public class ArticleReportService {
	private ArticleReportDAO_interface dao;
	private ArticleService articleSvc = new ArticleService();
	public ArticleReportService() {
		dao = new ArticleReportDAOHibernate();
	}

	public void insertArticleReport(int memberId , int articleId , String reportMsg) {
		ArticleReportBean bean = new ArticleReportBean();
		bean.setMemberId(memberId);
		bean.setArticleId(articleId);
		bean.setReportMsg(reportMsg);
		bean.setReportDateTemp(new java.util.Date());
		bean.setReportStatus(1);
		dao.insertArticleReport(bean);
	}

	public void deleteArticleReport(int articleId) {
		dao.deleteArticleReport(articleId);
	}

	public void updateArticleReportStatus(int articleId, int reportStatus) {
		dao.updateArticleReportStatus(articleId, reportStatus);

	}

	public ArticleReportBean getOneArticleReport(int articleReportId) {
		return dao.getOneArticleReport(articleReportId);

	}

	public List<ArticleReportBean> getAllArticleReport() {
		List<ArticleReportBean> beans = dao.getAllArticleReport(); 
		for(ArticleReportBean bean : beans){
			bean.setReportDate(articleSvc.convertDate(bean.getReportDateTemp()));
			int Status = bean.getReportStatus();
			if(Status == 1){
				bean.setReportStatuName("未處理");
			}else if(Status == 2){
				bean.setReportStatuName("<span style='color:#0000E3'>已處理</span>");
			}
		}
		return beans;
	}
	
	public List<ArticleReportBean> getAllArticleReport(int reportStatus) {
		List<ArticleReportBean> beans = dao.getAllArticleReport(reportStatus); 
		for(ArticleReportBean bean : beans){
			bean.setReportDate(articleSvc.convertDate(bean.getReportDateTemp()));
			if(reportStatus == 1){
				bean.setReportStatuName("未處理");
			}else if(reportStatus == 2){
				bean.setReportStatuName("<span style='color:#0000E3'>已處理</span>");
			}
		}
		return beans;
	}
	
	public boolean isReport(int memberId , int articleId){
		return dao.isReport(memberId, articleId);
	}

}
