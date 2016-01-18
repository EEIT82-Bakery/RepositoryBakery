package com.Voteaction.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Vote_status")
public class VoteStatusBean implements java.io.Serializable{
	@Id
	@Column(name="Vote_status_id")
	private int Vote_status_id;
	@Column(name="Vote_status")
	private String Vote_status;
	
	@OneToMany(mappedBy ="votestatusbean",cascade=CascadeType.REMOVE)
	private Set<VoteActionBean> voteactionbean;
	
	public Set<VoteActionBean> getVoteactionbean() {
		return voteactionbean;
	}
	public void setVoteactionbean(Set<VoteActionBean> voteactionbean) {
		this.voteactionbean = voteactionbean;
	}
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
		return  Vote_status_id +","+ Vote_status ;
	}
	
}
