package com.Voteaction.model;

import java.util.List;

public class VoteActionService {
	
		private VoteAction dao;
		
		public VoteActionService(){
			dao=new VoteActionJNDI();
		}
		
		public List<VoteActionBean> selectall(){
			
			return dao.selectall();
		}
		
		
		
		
		
		
}