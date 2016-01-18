package com.Voteaction.model;

import java.util.List;

public class VoteActionService {

	
	private VoteAction_Interface dao =new VoteActionHibernate();
	
	public List<VoteActionBean> selectall(){
			
			return dao.selectall();
		}
		
		
}