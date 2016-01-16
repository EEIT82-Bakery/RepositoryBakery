package com.Voteaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vote_action")
public class VoteStatusBean implements java.io.Serializable{
	@Id
	@Column(name="Vote_status_id")
	private int Vote_status_id;
	@Column(name="Vote_status")
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
