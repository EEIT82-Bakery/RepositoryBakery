package com.Voteaction.model;

import java.util.List;

public interface VoteAction {
	public abstract VoteActionBean insert(VoteActionBean bean);
	public abstract int update(String title,String describe,java.util.Date start,java.util.Date end,int voteid);
	public abstract int delete(int VoteId);
	public abstract List<VoteActionBean> selectall();
	public abstract VoteActionBean selectPk(int VoteId);
}
