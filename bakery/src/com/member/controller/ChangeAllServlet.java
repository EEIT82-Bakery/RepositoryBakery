package com.member.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/front/member/main/ChangePassword.controller")
public class ChangeAllServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CHANGEPASSWORD = "/front/member/main/CHGpassword.jsp";

	private MessageDigest messageDigest;

	public ChangeAllServlet() {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private MemberService memberservice = new MemberService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		String action = req.getParameter("action");

		if ("ChangePassword".equals(action)) {
			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);
			try {
				memberservice = new  MemberService();
			
				String oldpassword = req.getParameter("oldpassword");
				String newpassword = req.getParameter("newpassword");

				if (oldpassword == null || oldpassword.length() == 0) {
					errors.put("oldpassword", "密碼不能空白");
				}
				if (newpassword == null || newpassword.length() == 0) {
					errors.put("newpassword", "密碼不能空白");
				} else if (!newpassword.equals(req.getParameter("t_newpassword").trim())) {
					errors.put("newpassword", "確認密碼錯誤");

				}
				byte[] password = newpassword.getBytes();
				if (errors != null && !errors.isEmpty()) {
					req.getRequestDispatcher("/front/member/main/CHGpassword.jsp").forward(req, resp);
					return;
				}
				HttpSession session = req.getSession();
				String account = (String) session.getAttribute("account");
				
				MemberBean bean =memberservice.getAccount(account);
				
				
				
				boolean uppwd;
				uppwd = memberservice.changePassword(account, oldpassword, newpassword);
				if (uppwd != false) {		
					String path = req.getContextPath();
					resp.sendRedirect(path + "/index.jsp");
				}else{
						errors.put("oldpassword","密碼錯誤");
						req.getRequestDispatcher("/front/member/main/CHGpassword.jsp").forward(req, resp);	
				}
				
				
			} catch (Exception e) {
				errors.put("Exception",e.getMessage());
				req.getRequestDispatcher("/front/member/main/CHGpassword.jsp").forward(req, resp);
			}
			
		}

	}
		
	
	
	
}
