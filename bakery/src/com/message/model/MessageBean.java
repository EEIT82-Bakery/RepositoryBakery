package com.message.model;

import java.io.Serializable;
public class MessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer send_id;
	private Integer read_id; //收到的人
	private String msg_tit; //訊息的標題
	private String msg_cont; //訊息的內容
	private java.sql.Timestamp msg_date;
	private Integer msg_state;
	public Integer getSend_id() {
		return send_id;
	}
	public void setSend_id(Integer send_id) {
		this.send_id = send_id;
	}
	public Integer getRead_id() {
		return read_id;
	}
	public void setRead_id(Integer read_id) {
		this.read_id = read_id;
	}
	public String getMsg_tit() {
		return msg_tit;
	}
	public void setMsg_tit(String msg_tit) {
		this.msg_tit = msg_tit;
	}
	public String getMsg_cont() {
		return msg_cont;
	}
	public void setMsg_cont(String msg_cont) {
		this.msg_cont = msg_cont;
	}
	public java.sql.Timestamp getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(java.sql.Timestamp msg_date) {
		this.msg_date = msg_date;
	}
	public Integer getMsg_state() {
		return msg_state;
	}
	public void setMsg_state(Integer msg_state) {
		this.msg_state = msg_state;
	}			
}
