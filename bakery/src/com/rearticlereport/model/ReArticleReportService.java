package com.rearticlereport.model;

import java.util.List;

import com.article.model.ArticleService;

public class ReArticleReportService {
	private ReArticleReportDAO_interface dao;

	public ReArticleReportService() {
		dao = new ReArticleReportDAOHibernate();
	}

	public void insertReArticleReport(int memberId, int Id, String reportMsg) {
		ReArticleReportBean bean = new ReArticleReportBean();
		bean.setMemberId(memberId);
		bean.setId(Id);
		bean.setReportMsg(reportMsg);
		bean.setReportDate(new java.util.Date());
		bean.setReportStatus(1);
		dao.insertReArticleReport(bean);
	}

	public void deleteReArticleReport(int reReportId) {
		dao.deleteReArticleReport(reReportId);
	}

	public void updateReArticleReportStatus(int Id, int reportStatus) {
		dao.updateReArticleReportStatus(Id, reportStatus);
	}

	public ReArticleReportBean getOneReArticleReport(int reReportId) {
		return dao.getOneReArticleReport(reReportId);
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
		List<ReArticleReportBean> beans = dao.getAllReArticleReport(reportStatus);
		for (ReArticleReportBean bean : beans) {
			if (reportStatus == 1) {
				bean.setReportStatuName("未處理");
			} else if (reportStatus == 2) {
				bean.setReportStatuName("<span style='color:#0000E3'>已處理</span>");
			}
		}
		return beans;
	}

	public boolean isReReport(int memberId, int Id) {
		return dao.isReReport(memberId, Id);
	}

}
