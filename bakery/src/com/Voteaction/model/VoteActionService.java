package com.Voteaction.model;

import java.util.List;

public class VoteActionService {
	
<<<<<<< HEAD
		private VoteAction dao;
		
		public VoteActionService(){
			dao=new VoteActionJNDI();
		}
=======
		private VoteAction_Interface dao;
		
		
>>>>>>> branch 'master' of https://github.com/EEIT82-Bakery/RepositoryBakery.git
		
		public List<VoteActionBean> selectall(){
			
			return dao.selectall();
		}
		
		
		
		
		
		
}