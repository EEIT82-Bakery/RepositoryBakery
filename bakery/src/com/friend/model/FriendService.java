package com.friend.model;

import java.util.List;



public class FriendService {

	private FriendDAO dao;
	
	
	
	public FriendService() {
		dao = new FriendDAOJndi();
	}
	
	
	
	public FriendBean selecte(Integer invite_id,Integer invitee_id){
		return dao.selec(invite_id, invitee_id);
	}


	//**找出我的好友
	public List<FriendBean> selectFriendList(Integer invite_id){
		return dao.selectIsFriend(invite_id);
	}
	
	
	
	
	public int select(Integer invite_id,Integer invitee_id){
		return dao.select(invite_id, invitee_id);
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
	
	
	public void delete(Integer invite_id,Integer invitee_id){
		if(invite_id!=0 && invitee_id!=0) {
			dao.delete(invite_id, invitee_id);
	}
		
		
	//**

	

	}
	
}
