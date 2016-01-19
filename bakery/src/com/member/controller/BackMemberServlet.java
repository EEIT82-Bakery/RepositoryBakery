package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/BackAllMember.do")
public class BackMemberServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MemberService memberservice = new MemberService();
	private int PageIndex;
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doPost(req,resp);
	}





	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pageSize=5;
		int status=1;
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		
		String pageStr = req.getParameter("pages");
		int pageInt = Integer.parseInt(pageStr);
	
		List<MemberBean> list = memberservice.seletPage(pageInt);
		for(MemberBean bean : list){
			bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));	
			
		}
		
		req.setAttribute("bean", list);
		req.setAttribute("page", pageInt);
		int xxx= memberservice.getMemberCount();
		int pageCount = xxx/pageSize + (xxx % pageSize != 0 ? 1 : 0);
		req.setAttribute("pageCount", pageCount);
		req.getRequestDispatcher("/back/member/MemberMaintain.jsp").forward(req, resp);
		
		
	}
	

	
}
