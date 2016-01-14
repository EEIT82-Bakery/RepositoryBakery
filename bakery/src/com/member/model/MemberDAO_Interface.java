package com.member.model;

import java.util.List;

public interface MemberDAO_Interface {
	public MemberBean getOne(int memberid);
	public MemberBean getAccount(String account);
	public List<MemberBean> getALL();
	public MemberBean update(java.util.Date birth,String phone,String email,String address,String nickname,int memberid);
	public List<MemberBean> getAllMem(String account);
		
	//更新個人資料
	
	
	//-----------------------login---------------------//
	public boolean update(byte[] password,String account);//修改密碼
	
	//----------------------regis----------------------//
	public MemberBean insert(MemberBean bean); //註冊帳號
	boolean idExists(String account); //檢查帳號
	MemberBean findByAccount(String account);
	
	
	
	//--------------------------statu--------------//
	public MemberBean getMember(String account);
	//*-------------動態更新-----------------------*//
	public MemberBean update(String kanban,int memberid);
	
	//------------------後台-----------------------//
	public abstract List<MemberBean> selectByPage(int pageInt ,int status);
	
	public abstract int getMemberStatus(int status);
	
	
}
