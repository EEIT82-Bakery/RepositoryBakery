package com.Voteitem.model;

import java.util.List;

import com.Voteaction.model.VoteActionBean;

public interface Voteitem_Interface {

	public abstract void insert(int voteAtionId);
	public abstract void delete(int Vote_item_id);
	public abstract List<VoteitemBean> selectAll(VoteActionBean Vote_id);
	
}
