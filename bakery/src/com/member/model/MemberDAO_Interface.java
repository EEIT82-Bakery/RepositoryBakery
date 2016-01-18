package com.member.model;

import java.util.List;
import java.util.Map;

public interface MemberDAO_Interface {
	public MemberBean getOne(int memberid);
	public MemberBean getAccount(String account);
	public List<MemberBean> getALL();
	public void update(String phone,String email,String address,String nickname,byte[] picture ,int memberid);
	public List<MemberBean> getAllMem(String account);
		
	//更新個人資料
	
	
	//-----------------------login---------------------//
	public void updateUnifom(MemberBean bean);
	public boolean update(byte[] password,String account);//修改密碼
	public void  changePassword(MemberBean bean);
	
	//----------------------regis----------------------//
	public MemberBean insert(MemberBean bean); //註冊帳號
	boolean idExists(String account); //檢查帳號
	MemberBean findByAccount(String account);
	boolean emailExists(String email);//檢查信箱
	public MemberBean selectPassword(String account,String email); //以信箱以及帳號檢查密碼
	public List<String> getAllAccount();
	
	//--------------------------statu--------------//
	public MemberBean getMember(String account);
	//*-------------動態更新-----------------------*//
	public MemberBean update(String kanban,int memberid);
	
	//------------------後台-----------------------//
	public List<MemberBean> getAll(Map<String, String[]> map);
	//*更新狀態
	public void updateState(MemberBean bean);
	
	
	
	public abstract List<MemberBean> selectAllByid(int memberid);
	
	
	public abstract List<MemberBean> selectByPage(int pageInt ,int status);
	
	public abstract int getMemberStatus(int status);
	
	public abstract List<MemberBean> selectAllPage(int pageInt);
	public abstract int getAllMember();
	
}
