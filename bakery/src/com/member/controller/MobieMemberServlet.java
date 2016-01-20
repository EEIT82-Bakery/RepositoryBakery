package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/homeindex.do")
public class MobieMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		String account = req.getParameter("account");
		MemberService memberservice = new MemberService();
		MemberBean bean = memberservice.getAccount(account);
		bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));	
//		FriendBean friendbean = new FriendBean();
		
		
		req.setAttribute("member", bean);
		req.getRequestDispatcher("/front/memberforum/displaymember.jsp").forward(req, resp);
		// ----------------------------------------------------//
		}
		
			
			


		
		
	
	
	
}
