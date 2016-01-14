package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/front/member/login/login.do")
public class IsLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String LOGIN = "/front/member/login/login.jsp";
	private MemberService service = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		String action = req.getParameter("action");
		if ("login".equals(action)) {
			String account = req.getParameter("account").trim();
			String password = req.getParameter("password");
			Map<String, String> errorMsg = new HashMap<String, String>();
			req.setAttribute("errorsmap", errorMsg);
			PrintWriter out = resp.getWriter();
			if (account == null || account.length() == 0) {
				errorMsg.put("e_account", "請輸入帳號");
			}
			if (password == null || password.length() == 0) {
				errorMsg.put("e_password", "密碼不能為空白");
			}
			if (errorMsg != null && !errorMsg.isEmpty()) {
				req.getRequestDispatcher(LOGIN).forward(req, resp);
				return;
			}

			MemberBean memberbean = service.checkLogin(account, password);

			if (memberbean == null) {
				errorMsg.put("LoginError", "無此帳號或是帳號密碼錯誤");
				req.getRequestDispatcher(LOGIN).forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				memberbean.setMpicture(Base64.encodeBase64String(memberbean.getPicture()));
				session.setAttribute("isLogin", memberbean);
				int id = memberbean.getMember_id();
				session.setAttribute("id", id);
				String acc = memberbean.getAccount();
				session.setAttribute("account", acc);
				String sex = memberbean.getSex();
				if ("M".equals(sex)) {
					sex = "男";
				} else {
					sex = "女";
				}
				session.setAttribute("sex", sex);
				int sta = memberbean.getStatus();
				session.setAttribute("statu", sta);
				String path = req.getContextPath();
				resp.sendRedirect(path + "/index.jsp");
				resp.getWriter().write("True");
			}
		}
		if ("登出".equals(action)) {
			HttpSession session = req.getSession();
			if (session != null) {
				session.removeAttribute("isLogin");
			}
			String path = req.getContextPath();
			resp.sendRedirect(path + "/index.jsp");
		}

	}

}
