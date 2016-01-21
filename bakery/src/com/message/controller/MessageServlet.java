package com.message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleService;
import com.friend.model.FriendBean;
import com.friend.model.FriendService;
import com.member.model.MemberBean;
import com.message.model.MessageBean;
import com.message.model.MessageService;

@WebServlet("/MessageServlet.do")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		ArticleService articleSvc = new ArticleService();
		String action = req.getParameter("action");
		// if("add".equals(action)){
		// HttpSession session = req.getSession();
		// MemberBean bean = (MemberBean) session.getAttribute("isLogin");
		// }
		if ("select".equals(action)) {
			int pageSize = 5;
			HttpSession session = req.getSession();
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
			Integer memberid = bean.getMember_id();
			String pageStr = req.getParameter("pages");
			int pageInt = Integer.parseInt(pageStr);
			MessageService messageservice = new MessageService();
			List<MessageBean> list = messageservice.seletAllPage(pageInt,memberid);
			for (MessageBean be : list) {
				be.setMdate(articleSvc.convertDate(be.getMsg_date()));
			}
			req.setAttribute("list", list);
			req.setAttribute("page", pageInt);

			int allcount = messageservice.getReadCount(memberid);
			int pageCount = allcount / pageSize + (allcount % pageSize != 0 ? 1 : 0);
			req.setAttribute("pageCount", pageCount);
			req.getRequestDispatcher("/front/member/message/Message.jsp").forward(req, resp);
		}

		if ("selecread".equals(action)) {
			HttpSession session = req.getSession();
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
			Integer memberid = bean.getMember_id();
			int pageSize = 5;
			String pageStr = req.getParameter("pages");
			int pageInt = Integer.parseInt(pageStr);

			Integer status = new Integer(req.getParameter("statu"));
			MessageService messageservice = new MessageService();

			List<MessageBean> list = messageservice.seletPage(pageInt,
					memberid, status);
			for (MessageBean be : list) {
				be.setMdate(articleSvc.convertDate(be.getMsg_date()));
			}
			req.setAttribute("list", list);
			req.setAttribute("page", pageInt);
			int allcount = messageservice.getReadCount(memberid);
			int pageCount = allcount / pageSize + (allcount % pageSize != 0 ? 1 : 0);
			req.setAttribute("pageCount", pageCount);
			req.getRequestDispatcher("/front/member/message/Message.jsp").forward(req, resp);
		}

		if ("selecNoRead".equals(action)) {
			HttpSession session = req.getSession();
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
			Integer memberid = bean.getMember_id();		
			int pageSize = 5;
			String pageStr = req.getParameter("pages");
			int pageInt = Integer.parseInt(pageStr);
			MessageService messageservice = new MessageService();
			List<MessageBean> list = messageservice.seletPage(pageInt,
					memberid, 2);
			for (MessageBean be : list) {
				be.setMdate(articleSvc.convertDate(be.getMsg_date()));
			}
			req.setAttribute("list", list);
			req.setAttribute("page", pageInt);
			int allcount = messageservice.getReadCount(memberid);
			int pageCount = allcount / pageSize
					+ (allcount % pageSize != 0 ? 1 : 0);
			System.out.println(pageCount);
			req.setAttribute("pageCount", pageCount);
			req.getRequestDispatcher("/front/member/message/Message.jsp").forward(req, resp);
		}
		if ("count".equals(action)) {
			PrintWriter out = resp.getWriter();
			String msg_idTemp = req.getParameter("Msg_id");
			if(msg_idTemp == null || msg_idTemp.isEmpty()){
				out.println("<SCRIPT LANGUAGE='JavaScript'>");
				out.print("alert('沒有此文章');");
				out.println("</SCRIPT>");
				out.flush();
				out.close();
				String path = req.getContextPath();
			    resp.sendRedirect(path+"/front/member/message/Message.jsp");
			    return;
			}else{
				HttpSession session = req.getSession();
				MemberBean memberbean = (MemberBean)session.getAttribute("isLogin");
				Integer msg_id = Integer.parseInt(msg_idTemp);
				Integer member_id = memberbean.getMember_id();
			
				MessageBean messageBean = new MessageBean();
				messageBean.setMsg_id(msg_id);
				messageBean.setRead_id(memberbean.getMember_id());
				messageBean.setMsg_state(2);
				MessageService messageservice = new MessageService();
				messageservice.updateState(messageBean);
				MessageBean bean = messageservice.selectMessage(msg_id , member_id);
				
				FriendBean friendbean = new FriendBean();
				FriendService friendservice = new FriendService();
				friendbean = friendservice.selecte(bean.getSend_id(), bean.getRead_id());
				req.setAttribute("bean", bean);
				req.setAttribute("friendstatu", friendbean);
				req.getRequestDispatcher("/front/member/message/MessageCount.jsp").forward(req, resp);
			}
			

		}
	}
}
