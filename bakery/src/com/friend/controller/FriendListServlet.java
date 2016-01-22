package com.friend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.friend.model.FriendBean;
import com.friend.model.FriendService;


@WebServlet("/FriendListServlet.do")
public class FriendListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageSize = 5;
		String memberid = req.getParameter("invited");
		Integer invited = Integer.parseInt(memberid);
		String pageStr = req.getParameter("pages");
		int pageInt = Integer.parseInt(pageStr);
		FriendService friendservice = new FriendService();
		List<FriendBean> lists = friendservice.selectAllPage(pageInt, invited);
		for(FriendBean bean : lists){
			bean.setMinviteePicture(Base64.encodeBase64String(bean.getInviteePicture()));
		}
		req.setAttribute("list", lists);
		req.setAttribute("page", pageInt);
		int allcount = friendservice.allFriendCount(invited);
		int pageCount = allcount / pageSize + (allcount % pageSize != 0 ? 1 : 0);
		req.setAttribute("pageCount", pageCount);
		req.getRequestDispatcher("/front/member/friend_list/myfriend_list.jsp").forward(req, resp);	
		
		
		
		
		
	}
	

}
