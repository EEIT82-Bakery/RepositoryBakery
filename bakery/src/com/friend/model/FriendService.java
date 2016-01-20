package com.friend.model;

import com.message.model.MessageBean;

public class FriendService {

	private FriendDAO dao;
	
	
	
	public FriendService() {
		dao = new FriendDAOJndi();
	}



	public FriendBean insertFriend(Integer invite_id, Integer invitee_id,Integer friendstatu) {
		FriendBean bean = new FriendBean();
		bean.setInvite_id(invite_id);
		bean.setInvitee_id(invitee_id);
		bean.setFriendstatu(friendstatu);
		dao.insert(bean);
		return bean;
	
	}
	
	
	public int updatemessage(FriendBean bean){
		return dao.update(bean);
	}
	
	
}
