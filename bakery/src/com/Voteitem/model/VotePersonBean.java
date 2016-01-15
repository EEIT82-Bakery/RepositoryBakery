package com.Voteitem.model;

public class VotePersonBean implements java.io.Serializable{

	private int MemberId;
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
