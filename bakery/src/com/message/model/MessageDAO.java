package com.message.model;

import java.sql.Timestamp;
import java.util.List;

public interface MessageDAO {

	
	public MessageBean selectMessage(Integer msg_id , Integer member_id);
	public MessageBean select(Integer send_id,Integer read_id,Timestamp msg_date);
	
	public int insert(MessageBean bean);
	public int delete(Integer Msg_id);
	public int update(MessageBean bean);
	public int updateState(MessageBean bean);
	public List<MessageBean> selectAll();
	public List<MessageBean> getgivemymsg(Integer read_id,Integer msg_state );
	
	
	public List<MessageBean> selectAllPage(int pageInt,Integer read_id);
	
	
	public List<MessageBean> selectPage(int pageInt,Integer read_id,Integer msg_state);  //取得頁數
	public abstract int getState(Integer msg_state);  //取得總筆數
	public int getreadCount(Integer read_id);//取得收信者 收到信的數量
	public int getProduct();
	
	
	public MessageBean select();//不分狀態 取得全部的筆數
	
	
}
