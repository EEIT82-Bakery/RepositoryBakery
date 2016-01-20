package com.message.model;

import java.sql.Timestamp;
import java.util.List;



public class MessageService {
	
	public MessageDAO dao ;

	public MessageService() {
		dao = new MessageDAOJndi();
	}
	

	
	public MessageBean selectByTime(Integer send_id, Integer read_id, Timestamp msg_date){
		return dao.select(send_id, read_id, msg_date);
	}
	

	
	public int getStateCount(Integer msg_state){
		return 1;
	}
	
	
	public int insertmessage(MessageBean bean){
		return dao.insert(bean);	
	}
	

	public int updatemessage(MessageBean bean){
		return dao.update(bean);
	}
	
	
	//依照reader statu 來分頁數
	public List<MessageBean> seletPage(int pageInt,Integer read_id, Integer msg_state){
		
		return dao.selectPage(pageInt, read_id, msg_state);
	}	
	
	
	//依照reader 來分頁數
	public List<MessageBean> seletAllPage(int pageInt,Integer read_id){
		return dao.selectAllPage(pageInt, read_id);
		
	}
	

	
	
	
	public List<MessageBean> selectAllByStateAndId(Integer read_id, Integer msg_state){
		return dao.getgivemymsg(read_id, msg_state);
	}

	public int getMemberCount(){
		return dao.getProduct();
	}

	public int getReadCount(Integer read_id){
		return dao.getreadCount(read_id);
	}
	
	public List<MessageBean> selectMessage(){
		return dao.selectAll();
	}
	
	
	public MessageBean seletStatus(){
		return dao.select();
	}
	
	
}
