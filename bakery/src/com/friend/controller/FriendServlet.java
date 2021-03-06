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
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

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
		
		
		if("addfriend".equals(action)){
			String sinvite_id = req.getParameter("invite_id").trim();
			String sinvitee_id =req.getParameter("invitee_id").trim();
			
			if(sinvite_id==null||sinvite_id.length()==0){
				String path = req.getContextPath();
				resp.sendRedirect(path+"/front/article/error/NotLoginSendLogin.jsp");
				return;
			}
			
			Integer invite_id  = Integer.parseInt(sinvite_id);
			Integer invitee_id = Integer.parseInt(sinvitee_id);
			FriendBean bean = new FriendBean();
			bean.setInvite_id(invite_id);
			bean.setInvitee_id(invitee_id);	
			String msgtitle = req.getParameter("msgtitle");
			String msgcount = req.getParameter("msgcount");
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String title="會員:「";
			String title2="」提出好友申請";
			String count = "向您提出好友申請";		
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
			friendservice.insertFriend(invite_id, invitee_id,0);
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
			HttpSession session = req.getSession();
			try {
				Integer invite_id = new Integer(req.getParameter("invite_id"));
				Integer invitee_id = new Integer(req.getParameter("invitee_id"));
				String requestURL = req.getParameter("requestURL");
//				java.sql.Timestamp msg_date= java.sql.Timestamp.valueOf(req.getParameter("msg_date"));
				if (!errors.isEmpty()) {
					FriendBean bean = new FriendBean();
					bean.setInvite_id(invite_id);
					bean.setInvitee_id(invitee_id);
					req.setAttribute("friendBean", bean);
					req.getRequestDispatcher(requestURL).forward(req, resp);
					return;
				}
				FriendService friendservice = new FriendService();
				FriendBean friendbean = new FriendBean();
				friendbean.setInvite_id(invite_id);
				friendbean.setInvitee_id(invitee_id);
				friendbean.setFriendstatu(1);
				
				friendservice.updatemessage(friendbean);
			
				friendservice.insertFriend(invitee_id,invite_id, 1);
				MemberService memberservice = new MemberService();
				MemberBean memberbean = new MemberBean();
				memberbean = memberservice.getOneId(invite_id);
				
				Integer friendcount = friendservice.selectaddcount(invitee_id,0);
				session.setAttribute("friendcount", friendcount);	
				req.setAttribute("message" ,"已成為好友");
				req.setAttribute("memberBean", memberbean); 
				req.getRequestDispatcher(requestURL).forward(req, resp);
				
			} catch (Exception e) {
					errors.add(e.getMessage());
					req.getRequestDispatcher("/front/member/message.jsp").forward(req, resp);
					
				}
			
		}		
			if("noagree".equals(action)){
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
				Integer inviteid = new Integer(req.getParameter("invite_id"));
				Integer inviteeid = new Integer(req.getParameter("invitee_id"));
				String requestURL = req.getParameter("requestURL");
				FriendService friendservice = new FriendService();
				friendservice.delete(inviteid,inviteeid);
				MemberService memberservice = new MemberService();
				MemberBean memberbean = new MemberBean();
				memberbean = memberservice.getOneId(inviteid);
				req.setAttribute("memberBean", memberbean); 
				req.getRequestDispatcher(requestURL).forward(req, resp);
				}catch (Exception e) {
					errorMsgs.add(e.getMessage());
					 req.getRequestDispatcher("/front/member/message.jsp").forward(req, resp);
					
				}
			}
		

			
			if("myfriendlist".equals(action)){
				HttpSession session = req.getSession();
				MemberBean memberbean =(MemberBean) session.getAttribute("isLogin");
				if(memberbean==null){
					String path = req.getContextPath();
					resp.sendRedirect(path+"/front/article/error/NotLogin.jsp");
					return;
					
				}else{
				int pageSize = 8;
				Integer invited = memberbean.getMember_id();
				String pageStr = req.getParameter("pages");
				int pageInt = Integer.parseInt(pageStr);
				FriendService friendservice = new FriendService();
				List<FriendBean> lists = friendservice.selectAllPage(pageInt, invited);
				req.setAttribute("list", lists);
				req.setAttribute("page", pageInt);
				int allcount = friendservice.allFriendCount(invited);
				int pageCount = allcount / pageSize + (allcount % pageSize != 0 ? 1 : 0);
				req.setAttribute("pageCount", pageCount);
				req.getRequestDispatcher("/front/member/friend_list/myfriend_list.jsp").forward(req, resp);	
				}
			}
			
			
			
			if("deletefriend".equals(action)){		
				List<String> errors = new LinkedList<String>();
				req.setAttribute("errors", errors); 
				String page = req.getParameter("page");
				HttpSession session = req.getSession();
				MemberBean memberbean = (MemberBean)session.getAttribute("isLogin");
				String inviteeid = req.getParameter("invitee_id");
				if(memberbean==null || inviteeid==null){
					String path = req.getContextPath();
					resp.sendRedirect(path+"/front/article/error/NotLogin.jsp");
					return;
				}else{
					Integer invite_id = memberbean.getMember_id();
					Integer invitee_id = Integer.parseInt(inviteeid);
					FriendService friendservice = new FriendService();
					friendservice.realDelete(invite_id, invitee_id);
					resp.sendRedirect(req.getContextPath()+"/FriendListServlet.do?invited="+invite_id+"&pages="+page);
					
				}
		
			}
			
			
			if("friendlist".equals(action)){
				String memberid = req.getParameter("invited");
				if(memberid==null||memberid.trim().length()==0){
					resp.sendRedirect(req.getContextPath()+"/index.jsp");
					return;
				}else{
					int pageSize = 8;
					Integer invited = Integer.parseInt(memberid);
					String pageStr = req.getParameter("pages");
					int pageInt = Integer.parseInt(pageStr);
					FriendService friendservice = new FriendService();
					List<FriendBean> lists = friendservice.selectAllPage(pageInt, invited);
					
					 FriendBean beans = friendservice.selectOne(invited);
					req.setAttribute("beans",beans);
					req.setAttribute("list", lists);
					req.setAttribute("page", pageInt);
					int allcount = friendservice.allFriendCount(invited);
					int pageCount = allcount / pageSize + (allcount % pageSize != 0 ? 1 : 0);
					req.setAttribute("pageCount", pageCount);
					req.getRequestDispatcher("/front/member/friend_list/friend_list.jsp").forward(req, resp);	
				}
			}
			
			if("Select_addme".equals(action)){
				HttpSession session = req.getSession();
//				MemberBean memberbean = (MemberBean)session.getAttribute("isLogin");
//				Integer invitee_id = memberbean.getMember_id();
				Integer invitee_id = new Integer(req.getParameter("invitee_id"));
				FriendService friendservice = new FriendService();			
				List<FriendBean> lists = friendservice.getWhoInvitedMe(invitee_id, 0);
				session.setAttribute("list", lists);
				req.getRequestDispatcher("/front/member/friend_list/addme.jsp").forward(req, resp);
				
			}
			
			
			
		}
	}
	

