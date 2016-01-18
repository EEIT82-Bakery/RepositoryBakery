package com.kanban.model;

import java.util.List;



public interface KanbanDAO_Interface {
	public List<KanbanBean> select(int kanbanid);
	public void insert(KanbanBean bean);
	public void delete(int kanbanid);
	public KanbanBean update(KanbanBean bean);
	public List<KanbanBean> selectId(int memberid);

}
