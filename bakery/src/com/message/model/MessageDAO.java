package com.message.model;

import java.sql.Timestamp;
import java.util.List;

public interface MessageDAO {

	public MessageBean select(Integer send_id,Integer read_id,Timestamp msg_date);
	public int insert(MessageBean bean);
	public int delete(Integer send_id,Integer read_id,Timestamp msg_date);
	public MessageBean update(MessageBean bean);
	public List<MessageBean> selectAll();
	public List<MessageBean> getgivemymsg(Integer read_id,Integer msg_state );
	public List<MessageBean> selectPage(int pageInt);  //取得頁數
	public abstract int getState(Integer msg_state);  //取得總筆數
	public int getProduct();//不分狀態 取得全部的筆數
}
