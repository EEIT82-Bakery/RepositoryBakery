package com.message.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.model.MessageBean;
import com.message.model.MessageService;


@WebServlet("/delete.do")
public class DeleteMessageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		MessageService messageservice = new MessageService();
		if(action.equalsIgnoreCase("delete")){
			Map<String,String> error = new HashMap<String,String>();
			req.setAttribute("deleError", error);
			String page = req.getParameter("page");
			String[] xxx = req.getParameterValues("msg_id");
			if(xxx==null||xxx.length==0){
				error.put("error", "請勾選信件");
				req.getRequestDispatcher("MessageServlet.do?action=select&pages="+page).forward(req, resp);
				return;
			}
			for(String smsg_id : xxx){
				Integer msg_id = Integer.parseInt(smsg_id);
				messageservice.delete(msg_id);
				}
			resp.sendRedirect(req.getContextPath()+"/MessageServlet.do?action=select&pages="+page);
		}
		
	}
}
