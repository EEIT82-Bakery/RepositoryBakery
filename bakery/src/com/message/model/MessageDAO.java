package com.message.model;

import java.sql.Timestamp;
import java.util.List;

public interface MessageDAO {

	public MessageBean select(Integer send_id,Integer read_id,Timestamp msg_date);
	public int insert(MessageBean bean);
	public int delete(Integer send_id,Integer read_id,java.sql.Date msg_date);
	public MessageBean update(MessageBean bean);
	public List<MessageBean> selectAll();
	public List<MessageBean> getgivemymsg(Integer rece_id,Integer msg_state );
}
