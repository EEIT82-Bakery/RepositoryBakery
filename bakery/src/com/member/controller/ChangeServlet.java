package com.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;

import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/front/member/myimformation/test.controller")
public class ChangeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private MemberService memberservice = new MemberService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		HttpSession session = req.getSession();

		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("error", errors);

		int memberid = (int) session.getAttribute("id");

		String action = req.getParameter("action");
		String nickname = req.getParameter("nickname").trim();
		String nbirth = req.getParameter("birth").trim();
		String phone = req.getParameter("phone").trim();
		String email = req.getParameter("email").trim();
		String address = req.getParameter("address").trim();

		if (nickname == null || nickname.length() == 0) {
			errors.put("nickname", "暱稱不能空白");
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birth = null;

		try {
			birth = df.parse(nbirth);
		} catch (ParseException e) {
			e.printStackTrace();

		}

		if (phone == null || phone.length() == 0) {
			errors.put("phone", "請輸入手機號碼");

		} else if (!phone.matches("^09\\d{2}-?\\d{3}-?\\d{3}$")) {
			errors.put("phone", "且確認手機格式");
		}

		if (email == null || email.length() == 0) {
			errors.put("email", "請輸入信箱");
		} else if (!email.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
			errors.put("email", "格式請正確");
		}

		if (address == null || address.length() == 0) {
			errors.put("address", "請輸入地址，不能為空白");
		}

		if (errors != null && !errors.isEmpty()) {
			req.getRequestDispatcher("/front/member/myimformation/test.jsp").forward(req, resp);
			return;
		}
		MemberBean bean = new MemberBean();
		bean.setMember_id(memberid);
		bean.setNickname(nickname);
		bean.setBirth(birth);
		bean.setPhone(phone);
		bean.setEmail(email);
		bean.setAddress(address);
		if ("update".equals(action)) {
			bean = memberservice.updateimf(memberid, birth, phone, email, address, nickname);
			bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));
			session.setAttribute("SelectMo", bean);
			resp.sendRedirect(req.getContextPath() + "/front/member/myimformation/test.jsp");
		}
	}
}
