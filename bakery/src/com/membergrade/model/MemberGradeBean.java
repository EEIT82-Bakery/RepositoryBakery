package com.membergrade.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.member.model.MemberBean;


@Entity
@Table(name = "Member_Grade")
public class MemberGradeBean  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@OneToMany(
			mappedBy="membergrade",
			cascade={
					CascadeType.REMOVE
			}
			
			)
	private Set<MemberBean> members;
	
	
	public Set<MemberBean> getMembers() {
		return members;
	}
	public void setMembers(Set<MemberBean> members) {
		this.members = members;
	}
	
	@Id
	@Column(name="Statu")
	private int status;
	@Column(name="Permname")
	private String permname;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPermname() {
		return permname;
	}
	public void setPermname(String permname) {
		this.permname = permname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
