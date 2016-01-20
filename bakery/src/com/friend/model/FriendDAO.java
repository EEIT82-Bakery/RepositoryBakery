package com.friend.model;

import java.util.List;





public interface FriendDAO {
	public void insert(FriendBean bean);
	public List<FriendBean>getMyfriendsAll(Integer invite_id);//搜尋已成為我的好友
	
	public List<FriendBean>getMyInvite(Integer invite_id);//想查出我有發給別人邀請但別人還沒按接受或拒絕
	public List<FriendBean>getWhoInvitedMe(Integer invitee_id);//想查出我被某人發邀請但是我還沒有案接受或拒絕
	
	public int update(FriendBean bean);
	
}
