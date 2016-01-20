package com.friend.model;

public class FriendService {

	private FriendDAO dao;
	
	
	
	public FriendService() {
		dao = new FriendDAOJndi();
	}



	public FriendBean insertFriend(Integer invite_id, Integer invitee_id) {
		FriendBean bean = new FriendBean();
		bean.setInvite_id(invite_id);
		bean.setInvitee_id(invitee_id);
		dao.insert(bean);
		return bean;
	
	}
}
