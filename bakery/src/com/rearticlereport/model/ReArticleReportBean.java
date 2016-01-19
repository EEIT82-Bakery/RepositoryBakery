package com.rearticlereport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.member.model.MemberBean;

@Entity
@Table(name = "Re_Article_Report")
public class ReArticleReportBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;



	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MemberBean member;

	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Re_Report_Id")
	private int reReportId;

	@Column(name = "Article_Id")
	private int articleId;

	@Column(name = "Re_Id")
	private int reId;

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

	public int getReReportId() {
		return reReportId;
	}

	public void setReReportId(int reReportId) {
		this.reReportId = reReportId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getReId() {
		return reId;
	}

	public void setReId(int reId) {
		this.reId = reId;
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

	public String getReportStatuName() {
		return reportStatuName;
	}

	public void setReportStatuName(String reportStatuName) {
		this.reportStatuName = reportStatuName;
	}
}
