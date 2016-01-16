package com.Voteaction.model;

import java.util.List;

public interface VoteAction_Interface {
	public abstract void insert(VoteActionBean bean);
	public abstract void update(String title,String describe,java.util.Date end,int voteStatus,int voteid);
	public abstract void delete(int voteId);
	public abstract List<VoteActionBean> selectall();
	public abstract VoteActionBean selectPk(int VoteId);
}
