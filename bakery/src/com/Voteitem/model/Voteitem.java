package com.Voteitem.model;

import java.util.List;

import com.Voteaction.model.VoteActionBean;

public interface Voteitem {

	public abstract VoteitemBean insert(VoteitemBean itemBean);
	public abstract VoteitemBean update(VoteitemBean itemBean);
	public abstract int delete(int Vote_item_id);
	public abstract List<VoteitemBean> selectAll(VoteitemBean voteItemId);
	
	
	
}
