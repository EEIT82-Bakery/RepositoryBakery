package com.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.member.model.MemberBean;
import com.member.model.MemberService;


@WebServlet("/BackSelectServlet.do")
public class BackSelectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String PATH = "/back/member/MemberMaintain.jsp";
	private static final String PATHCorrec = "/back/member/MemberSelect.jsp";
	private static final String PATHSTATU = "/back/member/statusSelect.jsp";
	MemberService service = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		String action = req.getParameter("action");

		if ("selectid".equals(action)) {
			Map<String, String> errorMsg = new HashMap<String, String>();
			req.setAttribute("errors", errorMsg);

			try {
				String memid = req.getParameter("member_id");
				System.out.println(memid);
				int memberid = Integer.parseInt(memid);

				if (memberid == 0) {
					System.out.println("尋找失敗" + "2");
					errorMsg.put("rey", "請輸入id");

				}
				MemberBean lists = service.getOneId(memberid);

				System.out.println(lists);
				System.out.println(lists);
				if (lists == null) {
					System.out.println("尋找失敗" + "3");
					errorMsg.put("noData", "查無資料");
				}
				if (errorMsg == null || errorMsg.isEmpty()) {
					System.out.println(":hhhh");
					lists.setMpicture(Base64.encodeBase64String(lists.getPicture()));

					req.setAttribute("onelist", lists);
					req.getRequestDispatcher(PATHCorrec).forward(req, resp);
					return;
				}
				if (errorMsg != null && !errorMsg.isEmpty()) {

					req.getRequestDispatcher(PATH).forward(req, resp);
					return;
				}

			} catch (Exception e) {
				errorMsg.put("Null", "無法取得資料:" + e.getMessage());
				System.out.println("尋找失敗" + "1");
				req.getRequestDispatcher(PATH).forward(req, resp);

			}

		}
		System.out.println(action);
		if ("memberSearch".equals(action)) {
			
//			try {
				//取得查詢條件tempMap(immutable)，另存新map
				HttpSession session = req.getSession();
				HashMap<String, String[]> tempMap = (HashMap<String, String[]>)req.getParameterMap();
				HashMap<String, String[]> map = new HashMap<String, String[]>();
				map = (HashMap<String, String[]>) tempMap.clone();
				
				//查詢結果並存進session
				MemberService memSvc = new MemberService();
				List<MemberBean> memlist  = memSvc.getAllMember(map);
//				for(MemberBean bean : memlist){
//					bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));
//				}
				session.setAttribute("memlist", memlist);
				session.setAttribute("searchMemMap", map);
				req.getRequestDispatcher("/back/member/MemberSelect.jsp").forward(req, resp);
	
				
//			} catch (Exception e) {
//				req.getRequestDispatcher(PATH).forward(req, resp);
//				
//			}
		}
	
		if("disableMember".equals(action)){
			try{
			String memid = req.getParameter("member_id");
			int memberid = Integer.parseInt(memid);
			System.out.println(memberid);
			MemberService service = new MemberService();
			service.updateStatus(memberid, 2);
			String page = req.getParameter("page");
			resp.sendRedirect(req.getContextPath() + "/BackAllMember.do?pages=1");
			}catch (Exception e) {
			RequestDispatcher failureView = req.getRequestDispatcher("/BackAllMember.do?pages=1");
			failureView.forward(req, resp);
			}
		}
		
		if("activateMember".equals(action)){
			System.out.println(action);
			try{
				String memid = req.getParameter("member_id");
				int memberid = Integer.parseInt(memid);
				MemberService service = new MemberService();
				service.updateStatus(memberid, 3);
				String page = req.getParameter("page");
				resp.sendRedirect(req.getContextPath() + "/BackAllMember.do?pages=1");
				
			}catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/BackAllMember.do?pages=1");
				failureView.forward(req, resp);
			}
		}
		
		
		if("select_one".equals(action)){
			System.out.println(action);
	
			String mpicture=req.getParameter("mpicture");
			String member_id=req.getParameter("member_id");
			String account=req.getParameter("account");
			String username=req.getParameter("username");
			System.out.println("test="+username);
			String nickname=req.getParameter("nickname");
			System.out.println("test="+nickname);
			String phone=req.getParameter("phone");
			String address=req.getParameter("address");
			String order_math=req.getParameter("order_math");
			String status=req.getParameter("status");
			req.setAttribute("mpicture", mpicture);
			req.setAttribute("member_id", member_id);
			req.setAttribute("account", account);
			req.setAttribute("username", username);
			req.setAttribute("nickname", nickname);
			req.setAttribute("phone", phone);
			req.setAttribute("address", address);
			req.setAttribute("order_math", order_math);
			req.setAttribute("status", status);
			req.getRequestDispatcher("/back/member/MemberOne.jsp").forward(req, resp);
			
		}
	
	}
}
