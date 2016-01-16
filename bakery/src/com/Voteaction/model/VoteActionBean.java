package com.Voteaction.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Vote_action")
public class VoteActionBean implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Vote_id")
	private int voteId;
	@Column(name="Vote_title")
	private String voteTitle;
	@Column(name="Vote_describe")
	private String voteDescribe;
	@Column(name="Vote_start_date")
	private java.util.Date voteStartDate;
	@Column(name="Vote_end_date")
	private java.util.Date voteEndDate;
	@Column(name="Vote_status_id")
	private int voteStatus;
	
	
	public int getVoteStatus() {
		return voteStatus;
	}
	public void setVoteStatus(int voteStatus) {
		this.voteStatus = voteStatus;
	}
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	public String getVoteTitle() {
		return voteTitle;
	}
	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	public String getVoteDescribe() {
		return voteDescribe;
	}
	public void setVoteDescribe(String voteDescribe) {
		this.voteDescribe = voteDescribe;
	}
	public java.util.Date getVoteStartDate() {
		return voteStartDate;
	}
	public void setVoteStartDate(java.util.Date voteStartDate) {
		this.voteStartDate = voteStartDate;
	}
	public java.util.Date getVoteEndDate() {
		return voteEndDate;
	}
	public void setVoteEndDate(java.util.Date voteEndDate) {
		this.voteEndDate = voteEndDate;
	}
	
	@Override
	public String toString() {
		return  voteId +"," + voteTitle  +","+ voteDescribe+","
			+ voteStartDate  +","+ voteEndDate +"," + voteStatus;
	}
	
}
