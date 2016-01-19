package com.point.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.point.model.MemberBean;
import com.point.model.MemberHibernateDAO;
import com.point.model.MemberHibernateservice;


@WebServlet("/PointServlet.do")
public class PointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	MemberHibernateservice sr = new MemberHibernateservice(); 

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

	HttpSession session	=req.getSession(); //抓李傑的session
	com.member.model.MemberBean bean=(com.member.model.MemberBean)session.getAttribute("isLgoin");//抓他的登入
	System.out.println(bean);
	int id=bean.getPoint(); //取得禮節的memberid
	MemberBean poit = sr.updatePoint(id); //用我程式去修改他的點數id
//	System.out.println(poit.getPoint());
	req.setAttribute("upd", poit);// 我的name那邊定義的名字,與這邊的修改方法
	
	req.getRequestDispatcher(
			"/front/activity/Turntable.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
