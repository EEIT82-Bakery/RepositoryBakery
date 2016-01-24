package com.member.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.product.model.ProductBean;

public class MemberService {
	
	private MemberDAO_Interface dao;
	
	
	
	
	private MessageDigest messageDigest;
	public MemberService(){
		dao = new MemberDAOHibernate();
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	


	//****登入*****//
	public MemberBean checkLogin(String account, String password){
		MemberBean bean = dao.getMember(account);
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] pass = bean.getPassword(); // 資料庫抓出的密碼：亂碼
				byte[] temp = password.getBytes(); // 使用者輸入的密碼：明碼
				temp = messageDigest.digest(temp); // 使用者輸入的密碼：亂碼
				if (Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}
	
//	
//	public MemberBean chgPassword(String username, String oldPassword, String newPassword){
//		MemberBean bean = this.checkLogin(username, oldPassword);
//		if (bean != null) {
//			if (newPassword != null && newPassword.length() != 0) {
//				byte[] temp = newPassword.getBytes(); // 使用者輸入的密碼：明碼
//				temp = messageDigest.digest(temp); // 使用者輸入的密碼：亂碼
//				bean.setPassword(temp);
//				beans = dao.updateUnifom(bean);
//			}
//		return bean;
//		}
//		
//	}
	
	
	//**更改密碼**//
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		System.out.println(oldPassword);
		MemberBean bean = this.checkLogin(username, oldPassword);

		// String regex =
		// "^.*(?=.{6,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";
		// if(!newPassword.matches(regex)) {
		// System.out.println("password not match");
		// return false;
		// }

		if (bean != null) {
			System.out.println("is true");
			if (newPassword != null && newPassword.length() != 0) {
				byte[] temp = newPassword.getBytes(); // 使用者輸入的密碼：明碼
				temp = messageDigest.digest(temp); // 使用者輸入的密碼：亂碼
				System.out.println(temp);
				return dao.update(temp, username);
			}
		}
		return false;
	}
	
	//**查詢密碼
	public MemberBean updatePassword(String account, String password) {
		MemberBean bean = new MemberBean();
		bean.setAccount(account);
		byte[] pass = password.getBytes();
		byte[] temp = messageDigest.digest(pass);
		dao.update(temp, account);
		return bean;
	}
	
	
	//**修改個人資訊
	public MemberBean updateimf(String phone, String email, String address, String nickname,byte[] piture,int memberid) {
		return  dao.update(phone, email, address, nickname, piture,memberid);
	}
	
	//**修改個人動態狀態資訊
	public MemberBean updateKanban(int memberid, String kanban){
		MemberBean bean = new MemberBean();
		bean.setMember_id(memberid);
		bean.setKanban(kanban);
		bean = dao.update(kanban, memberid);
		return bean;		
	}
	//**取得會員狀態
	public MemberBean getOneM(String account){
		return dao.getAccount(account);
	}	
	//**取得一筆資訊
	public MemberBean getOneId(int memberid){
		System.out.println("service"+memberid);
		return dao.getOne(memberid);
	}	
	//**尋找資訊  以帳號
	public MemberBean getAccount(String account){
		return dao.getMember(account);
	}

	public MemberBean getPassword(String account,String email){
		return dao.selectPassword(account, email);
	}
	
	//**比對帳號密碼
	
	public MemberBean getAccountEmail(String account,String email){
		return dao.getMemberEmail(account, email);
	}
	
	//**取得有狀態的帳號  有join
	public MemberBean getMember(String account){
		return dao.getMember(account);
	}
	//**註冊**//
	public MemberBean addMember(String account, byte[] password, String username, String sex, java.util.Date birth,
			String email, String phone, String address, java.sql.Timestamp last_date, byte[] picture, int status,
			int point, int order_math, String nickname,String kanban) {
		MemberBean bean = new MemberBean();
		bean.setAccount(account);
		bean.setPassword(password);
		bean.setUsername(username);
		bean.setSex(sex);
		bean.setBirth(birth);
		bean.setEmail(email);
		bean.setPhone(phone);
		bean.setAddress(address);
		bean.setLast_date(last_date);
		bean.setPicture(picture);
		bean.setStatus(status);
		bean.setPoint(point);
		bean.setOrder_math(order_math);
		bean.setNickname(nickname);
		bean.setKanban(kanban);
		dao.insert(bean);
		return bean;
	}
	
	public boolean idExists(String account) {
	return dao.idExists(account);
	}
	
	public List<MemberBean> getAllMemBer() {
		return dao.getALL();
		
	}
	//**比對帳號
	public List<String> getAllMemAccount(){
		return dao.getAllAccount();
	}
	
	
	//**信箱比對
	public boolean emailExists(String email){
		return dao.emailExists(email);
	}
	
	
	//*會員的個人資訊
	public List<MemberBean> getAllMem(String account){
		List<MemberBean> bean = new ArrayList<>();
		List<MemberBean> mbean = dao.getAllMem(account);
		for(MemberBean smbean : mbean){
			smbean.setMember_id(smbean.getMember_id());
			smbean.setAccount(smbean.getAccount());
			smbean.setUsername(smbean.getUsername());
			smbean.setNickname(smbean.getNickname());
			smbean.setBirth(smbean.getBirth());
			smbean.setSex(smbean.getSex());
			smbean.setPhone(smbean.getPhone());
			smbean.setEmail(smbean.getEmail());
			smbean.setAddress(smbean.getAddress());
			smbean.setLogindate(smbean.getLogindate());
			String xxx = Base64.encodeBase64String(smbean.getPicture());
			smbean.setMpicture(xxx);
			smbean.setLast_date(smbean.getLast_date());
			smbean.setStatus(smbean.getStatus());
			smbean.setPoint(smbean.getPoint());
			smbean.setOrder_math(smbean.getOrder_math());
			smbean.setKanban(smbean.getKanban());
			bean.add(smbean);			
		}
		return bean;
	}
	
	
	public int getProductCount(int status){
		return dao.getMemberStatus(status);	
	}
	
	public List<MemberBean> selectMember(int pageInt,int status) {
		return dao.selectByPage(pageInt, status);
		
	}
	
	public int getMemberCount(){
		return dao.getAllMember();
	}
	public List<MemberBean> seletPage(int pageInt){
		
		return dao.selectAllPage(pageInt);
	}
	
	
	//******後台
	
	public List<MemberBean> select(int member_id){
		System.out.println("service成功");
		List<MemberBean> list = null;
		System.out.println("service:"+list);
		if(list==null){
		list = dao.selectAllByid(member_id);
		}
		
		return list;
		
	}
	public List<MemberBean> getAllMember(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	//*修改狀態
	public MemberBean updateStatus(int memberid,int status){
		MemberBean bean = new MemberBean();
		bean.setMember_id(memberid);
		bean.setStatus(status);
		dao.updateState(bean);
		return bean;
		
	}
	
	//**找圖
	public MemberBean selectImg(Integer invitee_id){
		return dao.selectImg(invitee_id);
	}
	
	
	
}
