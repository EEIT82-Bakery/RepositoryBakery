package com.kanban.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.kanban.model.KanbanService;
import com.member.model.MemberBean;


@MultipartConfig
@WebServlet("/Kanban.do")
public class KanbeanDoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	KanbanService service  = new KanbanService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		System.out.println(action);
		
		if("add".equals(action)){
			
			Map<String,String> error = new HashMap<String,String>();
			req.setAttribute("error", error);
			HttpSession session = req.getSession();
			
			MemberBean memberBean = (MemberBean) session.getAttribute("isLogin");
			int memberid = memberBean.getMember_id();
//			String member = req.getParameter("member_id");
//			int memberid = Integer.parseInt(member);
//			
			String  title = req.getParameter("title").trim();
			if(title==null||title.isEmpty()){
				error.put("error", "標題不得為空白");
			}
			
			String detail = req.getParameter("detail").trim();
			if(detail==null||detail.isEmpty()){
				error.put("error", "請輸入內容");
			}

			Part part = req.getPart("mypic");
			System.out.println("1");
			InputStream is = part.getInputStream();
			System.out.println("1");
			int imageSize = is.available();
			if (imageSize <= 0) {
				System.out.println("2");
				imageSize = is.available();
			}
			byte[] photo = null;
			if (imageSize <= 10 * 1024 * 1024) {
				System.out.println("3");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int nRead;
				photo = new byte[imageSize];
				while ((nRead = is.read(photo, 0, photo.length)) != -1) {
					baos.write(photo, 0, nRead);
				}
				baos.flush();
				baos.close();
			} else {
				error.put("errorphoto", "檔案過大,檔案必須小於 10 MB");
			}
			is.close();
			if (error == null || error.isEmpty()) {
				service.insetKanban(memberid, title, detail, photo);
			
				resp.sendRedirect(
						req.getContextPath() + "/KanbanServlet.do?v="+memberid);
			} else {
				req.getRequestDispatcher("/front/memberforum/KanbanServlet.do?v="+memberid).forward(req, resp);
			}
			
			
			
		}
		
	}
	
	
	
	
	

}
