package com.friend.model;

public class FriendBean {
	private Integer invite_id;//我
	private Integer invitee_id;//哪些好友
	private Integer friendstatu; //0.邀請  1.朋友 2.黑名單
	
	
	
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
