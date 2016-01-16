package com.Voteitem.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vote_item")
public class VoteitemBean implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Vote_item_id")
	private int voteItemId;
	@Column(name="Vote_item_name")
	private String voteItemName;
	@Column(name="Vote_item_photo")
	private byte[] voteItemPhoto;
	@Column(name="Vote_id")
	private int voteId ;
	
	
	
	public int getVoteItemId() {
		return voteItemId;
	}
	public void setVoteItemId(int voteItemId) {
		this.voteItemId = voteItemId;
	}
	public String getVoteItemName() {
		return voteItemName;
	}
	public void setVoteItemName(String voteItemName) {
		this.voteItemName = voteItemName;
	}
	public byte[] getVoteItemPhoto() {
		return voteItemPhoto;
	}
	public void setVoteItemPhoto(byte[] voteItemPhoto) {
		this.voteItemPhoto = voteItemPhoto;
	}

	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	
	
	@Override
	public String toString() {
		return  voteItemId + ", " + voteItemName + ","
		+ Arrays.toString(voteItemPhoto)  + "," + voteId ;
	}

	
	

}
