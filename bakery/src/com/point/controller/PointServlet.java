package com.point.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;

import com.member.model.MemberBean;
import com.point.model.PointService;

@WebServlet("/PointServlet.do")
public class PointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		MemberBean bean = (MemberBean) session.getAttribute("isLogin");
		if (bean != null) {
			int memberId = bean.getMember_id();
			PointService pointSvc = new PointService();
			String pointTemp = request.getParameter("point");
			if (pointTemp != null && !pointTemp.trim().isEmpty()) {
				int point = Integer.parseInt(pointTemp);
				if (point != 0) {
					bean = pointSvc.updatePoint(memberId, point);
					session.setAttribute("isLogin", bean);
				}
				JSONObject jsonObjectMary = new JSONObject(bean);
				out.print(jsonObjectMary);
				out.flush();
				out.close();
			}
		}

	}
}
