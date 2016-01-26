package com.friend.model;

public class FriendBean {
	private Integer invite_id;//我
	private Integer invitee_id;//哪些好友
	private Integer friendstatu; //0.邀請  1.朋友 2.黑名單
	
	private String inviteAccount;//join member
	private String inviteeAccount;//join member
	private byte[] invitePiture;
	private byte[] inviteePicture;
	private String MinvitePiture; //**用來裝照片 轉型用
	private String MinviteePicture;//**用來裝照片 轉型用
	private String inviteeKaban;//**我的好友的介紹
	private String inviteeNickname;//**我的好友的暱稱
	private String inviteNickname;
	
	
	

	public String getInviteNickname() {
		return inviteNickname;
	}
	public void setInviteNickname(String inviteNickname) {
		this.inviteNickname = inviteNickname;
	}
	public String getInviteeKaban() {
		return inviteeKaban;
	}
	public void setInviteeKaban(String inviteeKaban) {
		this.inviteeKaban = inviteeKaban;
	}
	public String getInviteeNickname() {
		return inviteeNickname;
	}
	public void setInviteeNickname(String inviteeNickname) {
		this.inviteeNickname = inviteeNickname;
	}
	public byte[] getInvitePiture() {
		return invitePiture;
	}
	public void setInvitePiture(byte[] invitePiture) {
		this.invitePiture = invitePiture;
	}
	public byte[] getInviteePicture() {
		return inviteePicture;
	}
	public void setInviteePicture(byte[] inviteePicture) {
		this.inviteePicture = inviteePicture;
	}
	public String getMinvitePiture() {
		return MinvitePiture;
	}
	public void setMinvitePiture(String minvitePiture) {
		MinvitePiture = minvitePiture;
	}
	public String getMinviteePicture() {
		return MinviteePicture;
	}
	public void setMinviteePicture(String minviteePicture) {
		MinviteePicture = minviteePicture;
	}
	public String getInviteAccount() {
		return inviteAccount;
	}
	public void setInviteAccount(String inviteAccount) {
		this.inviteAccount = inviteAccount;
	}
	public String getInviteeAccount() {
		return inviteeAccount;
	}
	public void setInviteeAccount(String inviteeAccount) {
		this.inviteeAccount = inviteeAccount;
	}
	public Integer getInvite_id() {
		return invite_id;
	}
	public void setInvite_id(Integer invite_id) {
		this.invite_id = invite_id;
	}
	public Integer getInvitee_id() {
		return invitee_id;
	}
	public void setInvitee_id(Integer invitee_id) {
		this.invitee_id = invitee_id;
	}
	public Integer getFriendstatu() {
		return friendstatu;
	}
	public void setFriendstatu(Integer friendstatu) {
		this.friendstatu = friendstatu;
	}
	
	
	
}
