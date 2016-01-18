package com.message.model;

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
	

}
