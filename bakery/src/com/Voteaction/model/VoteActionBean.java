package com.Voteaction.model;

import java.util.List;

public class VoteActionBean implements java.io.Serializable{

	
	private int voteId;
	private String voteTitle;
	private String voteDescribe;
	private java.util.Date voteStartDate;
	@Override
	public String toString() {
		return  voteId + voteTitle  + voteDescribe
				+  voteStartDate +  voteEndDate  ;
	}
	private java.util.Date voteEndDate;
//	private List<VoteitemBean> VoteitemBean;
	
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
	

	

	

}
