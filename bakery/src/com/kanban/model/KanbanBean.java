package com.kanban.model;

import java.io.Serializable;

public class KanbanBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int kanbanid;
	private int memberid;
	private String title;
	private String detail;
	private byte[] photo;
	private String Mphoto;
	
	
	
	
	
	
	
	public int getKanbanid() {
		return kanbanid;
	}
	public void setKanbanid(int kanbanid) {
		this.kanbanid = kanbanid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getMphoto() {
		return Mphoto;
	}
	public void setMphoto(String mphoto) {
		Mphoto = mphoto;
	}
	
	
	
	

}
