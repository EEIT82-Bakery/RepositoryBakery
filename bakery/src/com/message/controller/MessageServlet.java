package com.message.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleService;
import com.member.model.MemberBean;
import com.message.model.MessageBean;
import com.message.model.MessageService;


@WebServlet("/MessageServlet.do")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		
		String action = req.getParameter("action");
		
		
		if("add".equals(action)){
			HttpSession session = req.getSession();
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
	
			
		}
		
		
		
		
		if("select".equals(action)){			
			HttpSession session = req.getSession();
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
			Integer memberid =  bean.getMember_id();
			int pageSize=5;
			String pageStr = req.getParameter("pages");
			int pageInt = Integer.parseInt(pageStr);
			
			MessageService messageservice = new MessageService();
			MessageBean messagebean = new MessageBean();
			List<MessageBean> list = messageservice.seletAllPage(pageInt, memberid);
			for(MessageBean be :list){
				ArticleService articleSvc = new ArticleService();
				be.setMdate(articleSvc.convertDate(be.getMsg_date())); 

			}
			req.setAttribute("list", list);
			
			req.setAttribute("page", pageInt);
	
			int allcount= messageservice.getReadCount(memberid);
			int pageCount = allcount/pageSize + (allcount % pageSize != 0 ? 1 : 0);
			System.out.println(pageCount);
			req.setAttribute("pageCount", pageCount);
			req.getRequestDispatcher("/front/member/message/Message.jsp").forward(req, resp);
		}
		
		
		
		if("selecread".equals(action)){
			HttpSession session = req.getSession();
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
			Integer memberid =  bean.getMember_id();
			int pageSize=5;
			String pageStr = req.getParameter("pages");
			int pageInt = Integer.parseInt(pageStr);
			MessageService messageservice = new MessageService();
			MessageBean messagebean = new MessageBean();

			List<MessageBean> list = messageservice.seletPage(pageInt, memberid, 1);
			for(MessageBean be :list){
				ArticleService articleSvc = new ArticleService();
				be.setMdate(articleSvc.convertDate(be.getMsg_date())); 

			}
			
			req.setAttribute("list", list);
		
			req.setAttribute("page", pageInt);
			int allcount= messageservice.getReadCount(memberid);
			int pageCount = allcount/pageSize + (allcount % pageSize != 0 ? 1 : 0);
			req.setAttribute("pageCount", pageCount);
			req.getRequestDispatcher("/front/member/message/Message.jsp").forward(req, resp);
			}
			
		
		
		if("selecNoRead".equals(action)){
			System.out.println(action);
			HttpSession session = req.getSession();
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
			Integer memberid =  bean.getMember_id();
			int pageSize=5;
			String pageStr = req.getParameter("pages");
			int pageInt = Integer.parseInt(pageStr);
			MessageService messageservice = new MessageService();
			List<MessageBean> list = messageservice.seletPage(pageInt, memberid, 2);
			for(MessageBean be :list){
				ArticleService articleSvc = new ArticleService();
				be.setMdate(articleSvc.convertDate(be.getMsg_date())); 

			}
			req.setAttribute("list", list);
			req.setAttribute("page", pageInt);
			int allcount= messageservice.getReadCount(memberid);
			int pageCount = allcount/pageSize + (allcount % pageSize != 0 ? 1 : 0);
			System.out.println(pageCount);
			req.setAttribute("pageCount", pageCount);
			req.getRequestDispatcher("/front/member/message/Message.jsp").forward(req, resp);	
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	

}
