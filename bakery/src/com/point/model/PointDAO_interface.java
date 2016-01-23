package com.point.model;

import com.member.model.MemberBean;

public interface PointDAO_interface {
	public abstract MemberBean updatePoint(int member_id , int point);
}
