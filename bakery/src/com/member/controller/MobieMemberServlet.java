package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.article.model.ArticleBean;
import com.article.model.ArticleService;
import com.friend.model.FriendService;
import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/homeindex.do")
public class MobieMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		HttpSession session = req.getSession();
		MemberBean memberbean = (MemberBean) session.getAttribute("isLogin");
		if (memberbean == null) {
			resp.sendRedirect(req.getContextPath() + "/front/article/error/NotLogin.jsp");
			return;
		} else {
			String account = req.getParameter("account");
			Integer invite_id = memberbean.getMember_id();
			MemberService memberservice = new MemberService();
			MemberBean bean = memberservice.getAccount(account);
			bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));
			Integer invitee_id = bean.getMember_id();
			ArticleService articleservice = new ArticleService();
			List<ArticleBean> articlelist = articleservice.getMemberArticle(invitee_id);		
			FriendService friendservice = new FriendService();
			int statu = friendservice.select(invite_id, invitee_id);
			req.setAttribute("displayArticle", articlelist);
			req.setAttribute("statu", statu);
			req.setAttribute("member", bean);
			req.getRequestDispatcher("/front/memberforum/displaymember.jsp")
					.forward(req, resp);
		}
	}
}
