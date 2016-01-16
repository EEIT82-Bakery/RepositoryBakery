package com.Voteitem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Vote_Person")
public class VotePersonBean implements java.io.Serializable{
	
	@Id
	@Column(name = "Member_id")
	private int MemberId;
	@Id
	@Column(name="Vote_item_id")
	private int VoteItemId;
	
	
	public int getMemberId() {
		return MemberId;
	}
	public void setMemberId(int memberId) {
		MemberId = memberId;
	}
	public int getVoteItemId() {
		return VoteItemId;
	}
	public void setVoteItemId(int voteItemId) {
		VoteItemId = voteItemId;
	}
	
	@Override
	public String toString() {
		return "MemberId= " + MemberId + ", VoteItemId=" + VoteItemId ;
	}
	
}
