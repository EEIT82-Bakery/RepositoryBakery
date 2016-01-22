package com.membergrade.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.membergrade.model.MemberGradeBean;
import com.membergrade.model.MemberGradeService;

@WebServlet("/MemberGradeServlet.controller")
public class MemberGradeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	MemberGradeService mgservice = new MemberGradeService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = req.getSession();
		String statu = req.getParameter("statu");
		if(statu!=null){
		int status = Integer.parseInt(statu);
		System.out.println(status);	
		MemberGradeBean bean = mgservice.getStatus(status);
		req.setAttribute("STATU", bean);
		req.getRequestDispatcher("/front/member/s.jsp").forward(req, resp);
		}
	}
	

}
