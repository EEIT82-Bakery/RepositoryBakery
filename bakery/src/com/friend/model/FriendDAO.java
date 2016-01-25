package com.friend.model;

import java.util.List;









public interface FriendDAO {
	
	public FriendBean selec(Integer invite_id,Integer invitee_id);
	public void insert(FriendBean bean);
	public List<FriendBean>getMyfriendsAll(Integer invite_id);//搜尋已成為我的好友
	
	public List<FriendBean>getMyInvite(Integer invite_id);//想查出我有發給別人邀請但別人還沒按接受或拒絕
	public List<FriendBean>getWhoInvitedMe(Integer invitee_id,Integer friendstatu);//想查出我被某人發邀請但是我還沒有案接受或拒絕
	
	public int selectaddcount(Integer invitee_id,Integer friendstatu);//前台顯示幾個好友加您
	public int update(FriendBean bean);
	public void delete(Integer invite_id, Integer invitee_id); //**加好友時拒絕時刪除
	public int select(Integer invite_id, Integer invitee_id);
	public List<FriendBean> selectIsFriend(Integer invite_id);
	
	public FriendBean selectOne(Integer invite_id);
	public void RealDelete(Integer invite_id,Integer invitee_id);
	
	
	
	public List<FriendBean> selectAllPage(int pageInt,Integer invite_id);
	
	
	//**尋找總筆數  不管有無好友
	public int allFriendCount(Integer invite_id);
	
	//**0是 等待確認 1是好友
	public int CheckCount(Integer invite_id,Integer friendstatu);
	
	List<FriendBean> selectPage(int pageInt,Integer invite_id,Integer friendstatu);
	
	
}
