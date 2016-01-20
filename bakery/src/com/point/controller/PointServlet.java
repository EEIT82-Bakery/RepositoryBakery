package com.point.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.member.model.MemberBean;
import com.point.model.MemberHibernateDAO;
import com.point.model.MemberHibernateservice;

@WebServlet("/PointServlet.do")
public class PointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberHibernateservice sr = new MemberHibernateservice();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // 抓李傑的session
		MemberBean bean = (MemberBean) session.getAttribute("isLogin");// 抓他的登入
		int point = bean.getPoint();
		int mb = bean.getMember_id();
		System.out.println("servlet:" + point);
		MemberBean po = sr.updatePoint100(point); // 用我程式去修改他的點數id
		// System.out.println(poit.getPoint());
		req.setAttribute("upd", po);
		req.getRequestDispatcher("/front/activity/Jiugongge_OK.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
