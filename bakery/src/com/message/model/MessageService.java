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
	
	
//	public List<MessageBean> seletPage(int pageInt){
//		
//		return dao.selectPage(pageInt);
//	}	
	
	
	public List<MessageBean> selectAllByStateAndId(Integer read_id, Integer msg_state){
		return dao.getgivemymsg(read_id, msg_state);
	}

}
