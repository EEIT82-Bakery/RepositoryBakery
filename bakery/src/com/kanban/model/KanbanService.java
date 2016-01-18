package com.kanban.model;

import java.util.List;







public class KanbanService {
	private KanbanDAO_Interface kdao;
	
	
	
	
	public KanbanService() {
		kdao = new KanbanDAOJndi();
	}


	public List<KanbanBean> getSelect(int kanbanid){
		return kdao.select(kanbanid);	
	}
	
	public List<KanbanBean> getId(int memberid){
		return kdao.selectId(memberid);
	}
	
	
	
	public KanbanBean insetKanban(int memberId,String title,String detail,byte[] photo){
		KanbanBean xxx = new KanbanBean();
		xxx.setMemberid(memberId);
		xxx.setTitle(title);
		xxx.setDetail(detail);
		xxx.setPhoto(photo);
		kdao.insert(xxx);
		return xxx;
	}

	
	
}
