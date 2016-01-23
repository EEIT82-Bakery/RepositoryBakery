package com.point.model;

import com.member.model.MemberBean;

public class PointService {
	private PointDAO_interface dao;

	public PointService() {
		dao = new PointDAOHibernate();
	}

	public MemberBean updatePoint(int member_id , int point) {
		return dao.updatePoint(member_id , point);
	}

}
