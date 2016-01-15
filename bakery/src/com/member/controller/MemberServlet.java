package com.member.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/front/member/main/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberservice = new MemberService();

	//--//-/-/--//修改密碼md5//--//--//--//--//--//--//
	private static final String CHANGEPASSWORD = "/front/member/main/CHGpassword.jsp";
	private MessageDigest messageDigest;
	public MemberServlet() {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");

			HttpSession session = req.getSession();
//			int memberid = (int) session.getAttribute("id");
			String id = req.getParameter("id");
			System.out.println(id);
			int memberid  =Integer.parseInt(id);
			System.out.println(memberid);
			MemberBean bean = memberservice.getOneId(memberid);
			System.out.println(bean);
			bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));
			session.setAttribute("SelectMo", bean);
			resp.sendRedirect(req.getContextPath() + "/front/member/myimformation/test.jsp");
		
	}

}
