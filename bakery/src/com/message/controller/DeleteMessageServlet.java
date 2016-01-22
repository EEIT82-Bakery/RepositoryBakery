package com.message.controller;

import java.io.IOException;

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
			for(String smsg_id : req.getParameterValues("msg_id")){
				System.out.println(smsg_id);
				Integer msg_id = Integer.parseInt(smsg_id);
				messageservice.delete(msg_id);

			}

		}
		resp.sendRedirect("MessageServlet.do?action=select&pages=1");
		
	
	}
}
