package com.message.model;

import java.io.Serializable;

public class MessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer msg_id;
	private Integer send_id;
	private Integer read_id; // 收到的人
	private String msg_tit; // 訊息的標題
	private String msg_cont; // 訊息的內容
	private java.sql.Timestamp msg_date;
	private Integer msg_state;
	// **字串 把日期轉型放在這裡
	private String Mdate;
	// *memberbean
	private String sendAccount;

	private String readAccount;
	private byte[] sendPicture;
	private String sendNickname;
	
	private String readNickname;
	

	
	public String getReadNickname() {
		return readNickname;
	}

	public void setReadNickname(String readNickname) {
		this.readNickname = readNickname;
	}

	public byte[] getSendPicture() {
		return sendPicture;
	}

	public void setSendPicture(byte[] sendPicture) {
		this.sendPicture = sendPicture;
	}

	public String getSendNickname() {
		return sendNickname;
	}

	public void setSendNickname(String sendNickname) {
		this.sendNickname = sendNickname;
	}

	public Integer getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}

	public String getSendAccount() {
		return sendAccount;
	}

	public void setSendAccount(String sendAccount) {
		this.sendAccount = sendAccount;
	}

	public String getReadAccount() {
		return readAccount;
	}

	public void setReadAccount(String readAccount) {
		this.readAccount = readAccount;
	}

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

	public String getMdate() {
		return Mdate;
	}

	public void setMdate(String mdate) {
		Mdate = mdate;
	}

}
