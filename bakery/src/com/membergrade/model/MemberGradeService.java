package com.membergrade.model;

public class MemberGradeService {
	private MemberGradeDAO_Interface dao;

	public MemberGradeService(){
		dao= new MemberGradeDAOJndi();
	}
	
	public MemberGradeBean getStatus(int status){
		return dao.findByPrimaryKey(status);
	}
	
	
	
	
}
