package com.message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import com.member.model.MemberService;
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
		resp.setContentType("text/html; charset=utf-8");
		ArticleService articleSvc = new ArticleService();
		String action = req.getParameter("action");
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
			req.setAttribute("get",list);
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
			req.setAttribute("get",list);
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
			req.setAttribute("get",list);
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
				out.println("<script LANGUAGE='JavaScript'>");
				out.print("alert('沒有此文章');");
				out.println("</script>");
			    out.flush();
				String path = req.getContextPath();
			    resp.sendRedirect(path+"/front/member/message/Message.jsp");
				out.close();
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
		
		if("add".equals(action)){
			List<String> errors = new LinkedList<String>();
			req.setAttribute("errors", errors);
			HttpSession session = req.getSession();
			MemberBean memberbean = (MemberBean)session.getAttribute("isLogin");
			if(memberbean==null){
				String path = req.getContextPath();
				resp.sendRedirect(path+"/front/article/error/NotLogin.jsp");
				return;
			}	
			String msg_tit = req.getParameter("title");
			if (msg_tit == null || msg_tit.length() == 0) {
				errors.add("請輸入標題");
			}
			String msg_count = req.getParameter("count");
			if (msg_count == null || msg_count.length() == 0) {
				errors.add("請輸入內容");
			}
			if(!errors.isEmpty()){
				
			}
			
			
			
			Integer send_id = memberbean.getMember_id();
			Integer read_id = new Integer(req.getParameter("readid"));
			
			
			
			
			
		}
		if("addmsg".equals(action)){
			List<String> errors = new LinkedList<String>();
			req.setAttribute("errors", errors);
			HttpSession session = req.getSession();
			MemberBean memberbean = (MemberBean)session.getAttribute("isLogin");
			if(memberbean==null){
				String path = req.getContextPath();
				resp.sendRedirect(path+"/front/article/error/NotLogin.jsp");
				return;
			}	
			
			String msg_tit = req.getParameter("msg_tit");
			if (msg_tit == null || msg_tit.length() == 0) {
				errors.add("請輸入標題");
			}
			System.out.println(msg_tit);
			if(errors!=null&&!errors.isEmpty()){
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				return;
			}else{
				String msg_cont = req.getParameter("msg_cont");
				System.out.println(msg_cont);
				Integer send_id = memberbean.getMember_id();
				Integer read_id = new Integer(req.getParameter("readid"));
				java.sql.Timestamp send_date = new java.sql.Timestamp(System.currentTimeMillis());
				MessageBean messagebean = new MessageBean();
				messagebean.setSend_id(send_id);
				messagebean.setRead_id(read_id);
				messagebean.setMsg_tit(msg_tit);
				messagebean.setMsg_cont(msg_cont);
				messagebean.setMsg_date(send_date);
				messagebean.setMsg_state(1);
				MessageService messageserviece = new MessageService();
				messageserviece.insertmessage(messagebean);
				MemberService memberservice = new MemberService();
				memberbean = memberservice.getOneId(read_id);
				String account = memberbean.getAccount();
				req.setAttribute("memberVO", memberbean);	
				resp.sendRedirect(req.getContextPath()+"/homeindex.do?account="+account);
				
			}	
		}
			
	}
}
