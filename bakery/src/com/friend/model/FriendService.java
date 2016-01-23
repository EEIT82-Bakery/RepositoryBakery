package com.friend.model;

import java.util.List;



public class FriendService {

	private FriendDAO dao;
	
	public FriendService() {
		dao = new FriendDAOJndi();
	}
	
	
	public FriendBean selectOne(Integer invite_id){
		return dao.selectOne(invite_id);
	}
	
	//**尋找分區 謝謝
	public List<FriendBean> selectPage(int pageInt,Integer invite_id,Integer friendstatu){
		return dao.selectPage(pageInt, invite_id, friendstatu);
	}
	
	//**尋找不分區
	public List<FriendBean> selectAllPage(int pageInt,Integer invite_id){
		return dao.selectAllPage(pageInt, invite_id);
	}
	
	//**以狀態分好友數量
	public int CheckCount(Integer invite_id,Integer friendstatu){
		return dao.CheckCount(invite_id, friendstatu);
	}
	
	//**以自己id分出所有數量
	public int allFriendCount(Integer invite_id){
		return dao.allFriendCount(invite_id);
	}
	
	//**刪除好友
	public void realDelete(Integer invite_id,Integer invitee_id){
		if(invite_id!=0 && invitee_id!=0){
			dao.RealDelete(invite_id, invitee_id);
		}
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

	//**寄一封狀態為0的信件  代表未確定
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
	
	
	//**訊息時拒絕加入
	public void delete(Integer invite_id,Integer invitee_id){
		if(invite_id!=0 && invitee_id!=0) {
			dao.delete(invite_id, invitee_id);
		}
		


	
	}
}