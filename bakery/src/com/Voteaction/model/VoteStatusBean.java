package com.Voteaction.model;

public class VoteStatusBean implements java.io.Serializable{

	private int Vote_status_id;
	private String Vote_status;
	

	
	public int getVote_status_id() {
		return Vote_status_id;
	}
	public void setVote_status_id(int vote_status_id) {
		Vote_status_id = vote_status_id;
	}
	public String getVote_status() {
		return Vote_status;
	}
	public void setVote_status(String vote_status) {
		Vote_status = vote_status;
	}
	
	
	@Override
	public String toString() {
		return  Vote_status_id + Vote_status ;
	}
	
}
