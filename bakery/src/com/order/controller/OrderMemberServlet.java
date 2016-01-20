package com.order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.order.model.OrderBean;
import com.order.model.OrderService;


@WebServlet("/OrderMemberServlet.do")
public class OrderMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();
 	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session =request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute("isLogin");
		if(memberBean == null ){
			response.sendRedirect(request.getContextPath()+"/front/article/error/NotLogin.jsp");
		}else{
		int memberId= memberBean.getMember_id();
		List<OrderBean> lists = orderService.selectMember(memberId);
	 	request.setAttribute("listOrder", lists);
		
	 	String url = "/front/ordermember/orderlist.jsp";
		RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 orderlist.jsp
		successView.forward(request, response);
		}

	}

}
