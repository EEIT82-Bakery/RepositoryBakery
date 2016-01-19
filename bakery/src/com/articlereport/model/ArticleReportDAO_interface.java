package com.articlereport.model;

import java.util.List;
public interface ArticleReportDAO_interface {
	public abstract void insertArticleReport(ArticleReportBean bean);
	public abstract void deleteArticleReport(int articleId);
	public abstract void updateArticleReportStatus(int articleId , int reportStatus);
	public abstract ArticleReportBean getOneArticleReport(int articleReportId);
	public abstract boolean isReport(int memberId , int articleId);
	public abstract List<ArticleReportBean> getAllArticleReport();
	public abstract List<ArticleReportBean> getAllArticleReport(int reportStatus);
}
