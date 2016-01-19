package com.kanban.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.kanban.model.KanbanBean;
import com.kanban.model.KanbanService;
import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/KanbanServlet.do")
public class KanbanServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	KanbanService service  = new KanbanService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		
			String account = req.getParameter("v");
			int member_id = Integer.parseInt(account);
			MemberService memberservice = new MemberService();
			MemberBean bean = memberservice.getOneId(member_id);
			bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));	
			KanbanService kanbanservice = new KanbanService();		
			List<KanbanBean> kanbanbeans = kanbanservice.getId(member_id);
			for(KanbanBean kbean : kanbanbeans){
				kbean.setMphoto(Base64.encodeBase64String(kbean.getPhoto()));			
			}	
			
			req.setAttribute("kanban", bean);
			req.setAttribute("list", kanbanbeans);
			req.getRequestDispatcher("/front/memberforum/kanban.jsp").forward(req, resp);			
			
	}

}
