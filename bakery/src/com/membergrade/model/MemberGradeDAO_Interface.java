package com.membergrade.model;

import java.util.List;
import java.util.Set;

import com.member.model.MemberBean;

public interface MemberGradeDAO_Interface {
	   public void update(MemberGradeBean memberGradeBean);
	   public void delete(int memberid);
	   public MemberGradeBean findByPrimaryKey(int status);
	   public List<MemberGradeBean> getAll();
	   public Set<MemberBean> getEmpsByDeptno(int status);
}
