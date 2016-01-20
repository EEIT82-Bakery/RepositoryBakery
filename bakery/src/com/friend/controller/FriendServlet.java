package com.friend.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friend.model.FriendBean;
import com.friend.model.FriendService;
import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.message.model.MessageBean;
import com.message.model.MessageService;

@WebServlet("/FriendServlet.controller")
public class FriendServlet extends HttpServlet{
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
		resp.setContentType("text/plain; charset=utf-8");
		
		String action = req.getParameter("action");
		
		System.out.println(action);
		if("addfriend".equals(action)){
			Integer invite_id = new Integer(req.getParameter("invite_id"));
			System.out.println("?!="+invite_id);
			Integer invitee_id = new Integer(req.getParameter("invitee_id"));
			System.out.println("?!!!="+invitee_id);
			
			FriendBean bean = new FriendBean();
			bean.setInvite_id(invite_id);
			bean.setInvitee_id(invitee_id);
			
			String msgtitle = req.getParameter("msgtitle");
			String msgcount = req.getParameter("msgcount");
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String title="來自會員:「";
			String title2="」的新消息";
			String count = "向您發送好友申請";
			
			MessageBean messagebean = new MessageBean();
			messagebean.setSend_id(invite_id);
			messagebean.setRead_id(invitee_id);
			messagebean.setMsg_tit(title+msgtitle+title2);
			messagebean.setMsg_cont(msgcount+count);
			messagebean.setMsg_date(date);
			messagebean.setMsg_state(1);
		
	
			MemberService memberservice = new MemberService();
			MemberBean memberbean = new MemberBean();
			
			
			
			FriendService friendservice = new FriendService();
			friendservice.insertFriend(invite_id, invitee_id);
			memberbean = memberservice.getOneId(invitee_id);
			req.setAttribute("memberVO", memberbean);
			
			
			String account = memberbean.getAccount();
			MessageService messageserviece = new MessageService();
			messageserviece.insertmessage(messagebean);
			req.getRequestDispatcher("/homeindex.do?account="+account).forward(req, resp);
			
			
		}
		
		//同意加入
		if("agree".equals(action)){
			
			List<String> errors = new LinkedList<String>();
			req.setAttribute("errors", errors);
			String requestURL = req.getParameter("requestURL");
			try {
				Integer invite_id = new Integer(req.getParameter("invite_id"));
				Integer invitee_id = new Integer(req.getParameter("invitee_id"));
				java.sql.Timestamp msg_date= java.sql.Timestamp.valueOf(req.getParameter("msg_date"));
				if (!errors.isEmpty()) {
					FriendBean bean = new FriendBean();
					bean.setInvite_id(invite_id);
					bean.setInvitee_id(invitee_id);
					req.setAttribute("friendBean", bean);RequestDispatcher failureView = req
							.getRequestDispatcher("/front/friendList/showWhoInvitedMe.jsp");
					failureView.forward(req, resp);
					return;
				}
				FriendService friendservice = new FriendService();
				friendservice.insertFriend(invite_id, invitee_id);
				MemberService memberservice = new MemberService();
				MemberBean memberbean = new MemberBean();
				memberbean = memberservice.getOneId(invite_id);
				req.setAttribute("memberBean", memberbean); 
				RequestDispatcher successView = req.getRequestDispatcher("/MessageServlet.do?action=count&send_id="+invite_id+"&read_id="+invitee_id+"&msg_date="+msg_date);
				successView.forward(req, resp);
				
			
			} catch (Exception e) {
					errors.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front/friend_list/showWhoInvitedMe.jsp");
					failureView.forward(req, resp);
					
				}
		}
		
		
		
		
	}
	
	
}
