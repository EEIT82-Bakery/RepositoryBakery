package com.rearticlereport.model;

import java.util.List;

public class ReArticleReportService {
	private ReArticleReportDAO_interface dao;

	public ReArticleReportService() {
		dao = new ReArticleReportDAOHibernate();
	}

	public void insertReArticleReport(int memberId, int articleId, int reId,
			String reportMsg) {
		ReArticleReportBean bean = new ReArticleReportBean();
		bean.setMemberId(memberId);
		bean.setArticleId(articleId);
		bean.setReId(reId);
		bean.setReportMsg(reportMsg);
		bean.setReportDate(new java.util.Date());
		bean.setReportStatus(1);
		dao.insertReArticleReport(bean);
	}

	public void deleteReArticleReport(int articleId , int reId) {
		dao.deleteReArticleReport(articleId , reId);
	}

	public void updateReArticleReportStatus(int articleId, int reId, int reportStatus) {
		dao.updateReArticleReportStatus(articleId, reId, reportStatus);
	}

	public ReArticleReportBean getOneReArticleReport(int articleReportId) {
		return dao.getOneReArticleReport(articleReportId);
	}

	public List<ReArticleReportBean> getAllReArticleReport() {
		List<ReArticleReportBean> beans = dao.getAllReArticleReport();
		
		for (ReArticleReportBean bean : beans) {
			int Status = bean.getReportStatus();
			System.out.println(beans);
			if (Status == 1) {
				bean.setReportStatuName("未處理");
			} else if (Status == 2) {
				bean.setReportStatuName("<span style='color:#0000E3'>已處理</span>");
			}
		}
		return beans;
	}

	public List<ReArticleReportBean> getAllReArticleReport(int reportStatus) {
		List<ReArticleReportBean> beans = dao.getAllReArticleReport();
		for (ReArticleReportBean bean : beans) {
			if (reportStatus == 1) {
				bean.setReportStatuName("未處理");
			} else if (reportStatus == 2) {
				bean.setReportStatuName("<span style='color:#0000E3'>已處理</span>");
			}
		}
		return beans;
	}

	public boolean isReReport(int memberId, int articleId, int reId) {
		return dao.isReReport(memberId, articleId, reId);
	}

}
