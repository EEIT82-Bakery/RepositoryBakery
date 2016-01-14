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

@WebServlet("/BaackMemberServlet.do")
public class BackMemberServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MemberService memberservice = new MemberService();
	private int PageIndex;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pageSize=6;
		int status=1;
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		
		String statu = req.getParameter("status");
		
		
		
		
		
		
		
		
		
	
			
		
		
	}
	

	
}
