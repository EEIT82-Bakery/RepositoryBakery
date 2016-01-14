package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/AllMemberServlet.do")
public class AllMemberServlet extends  HttpServlet{

	private static final long serialVersionUID = 1L;

	MemberService memberservice = new MemberService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		
	
		MemberBean bean = new MemberBean();
		
		String account = req.getParameter("account");		
	
			List<MemberBean> result = memberservice.getAllMem(account);
			req.setAttribute("ss", result);
			req.getRequestDispatcher("/front/memberforum/userimfo.jsp").forward(req, resp);
//			String path = req.getContextPath();
//			resp.sendRedirect(path+"/front/memberforum/displaymember.jsp");
		
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		
	}

	

	

}
