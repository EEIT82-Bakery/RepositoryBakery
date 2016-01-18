package com.Voteitem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Vote_Person")
public class VotePersonBean implements java.io.Serializable{
	
	@Id
	@TableGenerator(name="MID" ,table="Member")
	@GeneratedValue(generator="MID",strategy=GenerationType.TABLE)
	private int MemberId;
	@Id
	@TableGenerator(name="Vid" ,table="Vote_item")
	@GeneratedValue(generator="Vid",strategy=GenerationType.TABLE)
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
