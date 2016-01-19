package com.rearticlereport.model;

import java.util.List;

public interface ReArticleReportDAO_interface {
	public abstract void insertReArticleReport(ReArticleReportBean bean);

	public abstract void deleteReArticleReport(int articleId, int reId);

	public abstract void updateReArticleReportStatus(int articleId, int reId,
			int reportStatus);

	public abstract ReArticleReportBean getOneReArticleReport(
			int reReportId);

	public abstract boolean isReReport(int memberId, int articleId, int reId);

	public abstract List<ReArticleReportBean> getAllReArticleReport();

	public abstract List<ReArticleReportBean> getAllReArticleReport(
			int reportStatus);
}
