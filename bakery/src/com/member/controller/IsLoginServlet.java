package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.mail.JavaMailUtil;
import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.member.model.RandomPassWord;

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
		
		String action = req.getParameter("action");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		if ("login".equals(action)) {
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/plain; charset=utf-8");
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
		System.out.println(action);
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			if (session != null) {
				session.removeAttribute("isLogin");
			}
			String path = req.getContextPath();
			resp.sendRedirect(path + "/index.jsp");
		}

		
			if("select".equals(action)){
			
			String email  = req.getParameter("email");
			System.out.println(email);
			String account  = req.getParameter("account");
			System.out.println(account);
			Map<String,String> errors = new HashMap<String,String>();
			req.setAttribute("errors", errors);
			
			
			if(email==null||email.length()==0){
				System.out.println("ERROR1");
				errors.put("email_error", "請輸入信箱");
			}
			if(account==null||account.length()==0){
				System.out.println("ERROR2");
				errors.put("account_error", "請輸入帳號");
			}
			if(errors!=null&&!errors.isEmpty()){
				System.out.println("ERROR3");
				req.getRequestDispatcher("/front/member/login/selectPassword.jsp").forward(req, resp);
				return;
			}
			System.out.println("no error");
			service = new MemberService();			
			MemberBean bean = service.getAccount(account);					
			if (bean == null) {
				errors.put("account", "無此帳號");
				req.getRequestDispatcher("/front/member/login/selectPassword.jsp").forward(req, resp);
				return;
			}	
			
			
			if (bean != null && email.equals(bean.getEmail())) {
				String name = bean.getUsername();
				System.out.println("name:"+name);
				
				String mem_psw = RandomPassWord.getRandomPassWord(7);
				service.updatePassword(account, mem_psw);
				String to = email;
				String from = "a0938988920@gmail.com";
				String subject = "會員密碼通知";
				List<String> attachment = Arrays.asList(
						   new String[]{
								
								   });;
				String messageText = name + "先生/小姐 您好，請謹記此密碼: " + mem_psw;
				JavaMailUtil  util = new JavaMailUtil(from, to,subject,messageText,attachment);
				
				if (util.send()){
					   System.out.println("發信成功");
					} else {
					   System.out.println("發信失敗");
					}
				req.getRequestDispatcher("/front/member/login/login.jsp").forward(req,resp);
				}					
			}
				
	
			
			
			
			
		}
		
		
		
	}


