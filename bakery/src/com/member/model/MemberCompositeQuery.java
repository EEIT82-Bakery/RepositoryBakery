package com.member.model;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class MemberCompositeQuery {
	
	public static Criteria getCriteria(Criteria query, String columnName,String value) {
		
		if ("Member_id".equals(columnName) || "Statu".equals(columnName) || "Point".equals(columnName)) {
			query.add(Restrictions.eq(columnName, new Integer(value)));
		} else if ("Account".equals(columnName) || "Nickname".equals(columnName) || "Username".equals(columnName) || 
				   "Phone".equals(columnName) || "Sex".equals(columnName) || "Email".equals(columnName)) {
			query.add(Restrictions.like(columnName, "%"+value+"%"));
		} else if ("IS NOT NULL".equals(value)) {
				query.add(Restrictions.isNotNull(columnName));
			}
		
		return query;
	}
}
