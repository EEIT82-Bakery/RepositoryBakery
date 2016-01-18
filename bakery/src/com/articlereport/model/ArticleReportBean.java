package com.articlereport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Article_Report")
public class ArticleReportBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Article_Report_Id")
	private int articleReportId;

	@Column(name = "Article_Id")
	private int articleId;

	@Column(name = "Member_Id")
	private int memberId;

	@Column(name = "Report_Msg")
	private String reportMsg;

	@Column(name = "Report_Date")
	private java.util.Date reportDate;

	@Column(name = "Report_Status")
	private int reportStatus;

	@Transient
	private String reportStatuName;

	public String getReportStatuName() {
		return reportStatuName;
	}

	public void setReportStatuName(String reportStatuName) {
		this.reportStatuName = reportStatuName;
	}

	public int getArticleReportId() {
		return articleReportId;
	}

	public void setArticleReportId(int articleReportId) {
		this.articleReportId = articleReportId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getReportMsg() {
		return reportMsg;
	}

	public void setReportMsg(String reportMsg) {
		this.reportMsg = reportMsg;
	}

	public java.util.Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(java.util.Date reportDate) {
		this.reportDate = reportDate;
	}

	public int getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

}
