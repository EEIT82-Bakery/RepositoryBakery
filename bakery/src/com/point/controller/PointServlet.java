package com.point.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import com.member.model.MemberBean;
import com.member.model.MemberDAOHibernate;
import com.point.model.MemberHibernateDAO;
import com.point.model.MemberHibernateservice;

@WebServlet("/PointServlet.do")
public class PointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberHibernateservice sr = new MemberHibernateservice();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(); // 抓豊傑的session
		MemberBean bean = (MemberBean) session.getAttribute("isLogin");// 抓他的登入
		int mbId = bean.getMember_id();
		MemberBean po = sr.selectp100(mbId); // 用我程式去修改他的點數id
		session.setAttribute("isLogin", po);
		po.setMpicture(Base64.encodeBase64String(po.getPicture()));
		JSONObject jsonObjectMary = new JSONObject(po);	
		out.print(jsonObjectMary);
		// System.out.println(poit.getPoint());
//		req.setAttribute("upd", po);
//		req.getRequestDispatcher("/front/activity/Jiugongge_OK.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
