package com.message.model;

import java.util.List;



public class MessageService {
	
	public MessageDAO dao ;

	public MessageService() {
		dao = new MessageDAOJndi();
	}
	
	public int getStateCount(Integer msg_state){
		return 1;
	}
	
	
	public int insertmessage(MessageBean bean){
		return dao.insert(bean);	
	}
	

	public List<MessageBean> seletPage(int pageInt,Integer read_id, Integer msg_state){
		
		return dao.selectPage(pageInt, read_id, msg_state);
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
