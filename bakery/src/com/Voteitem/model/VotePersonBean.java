package com.Voteitem.model;

<<<<<<< HEAD
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
=======
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="Vote_Person")
public class VotePersonBean implements java.io.Serializable{
	@Id
	@TableGenerator(name="")
	private int MemberId;
>>>>>>> branch 'master' of https://github.com/EEIT82-Bakery/RepositoryBakery.git
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
