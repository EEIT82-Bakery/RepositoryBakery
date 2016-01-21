package com.rearticlereport.model;

import java.util.List;

public interface ReArticleReportDAO_interface {
	public abstract void insertReArticleReport(ReArticleReportBean bean);

	public abstract void deleteReArticleReport(int reReportId);

	public abstract void updateReArticleReportStatus(int Id, int reportStatus);

	public abstract ReArticleReportBean getOneReArticleReport(int reReportId);

	public abstract boolean isReReport(int memberId, int Id);

	public abstract List<ReArticleReportBean> getAllReArticleReport();

	public abstract List<ReArticleReportBean> getAllReArticleReport(int reportStatus);
}
