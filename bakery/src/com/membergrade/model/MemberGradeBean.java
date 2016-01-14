package com.membergrade.model;

public class MemberGradeBean  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int status;
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
