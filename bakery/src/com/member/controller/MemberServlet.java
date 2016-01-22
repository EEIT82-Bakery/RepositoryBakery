package com.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import org.apache.commons.codec.binary.Base64;

import com.member.model.MemberBean;
import com.member.model.MemberService;

@WebServlet("/front/member/main/member.do")
@MultipartConfig
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberservice = new MemberService();
	private MessageDigest messageDigest;

	public MemberServlet() {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		if ("select".equals(action)) {
			HttpSession session = req.getSession();
			int memberid = (int) session.getAttribute("id");
			MemberBean bean = memberservice.getOneId(memberid);
			bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));
			session.setAttribute("SelectMo", bean);
			resp.sendRedirect(req.getContextPath() + "/front/member/myimformation/test.jsp");
		}

		if ("update".equals(action)) {
			HttpSession session = req.getSession();
			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("error", errors);
			int memberid = (int) session.getAttribute("id");
			MemberBean beans = memberservice.getOneId(memberid);
			String nickname = req.getParameter("nickname").trim();
			String phone = req.getParameter("phone").trim();
			String email = req.getParameter("email").trim();
			String address = req.getParameter("address").trim();
			if (nickname == null || nickname.length() == 0) {
				errors.put("nickname", "暱稱不能空白");
			}
			if (phone == null || phone.length() == 0) {
				errors.put("phone", "請輸入手機號碼");
			} else if (!phone.matches("^09\\d{2}-?\\d{3}-?\\d{3}$")) {
				errors.put("phone", "且確認手機格式");
			}
			if (email == null || email.length() == 0) {
				errors.put("email", "請輸入信箱");
			} else if (!email
					.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
				errors.put("email", "格式請正確");
			}
			if (address == null || address.length() == 0) {
				errors.put("address", "請輸入地址，不能為空白");
			}

			Part part = req.getPart("pic");
			InputStream is = part.getInputStream();
			byte[] mem_pic = null;
			int imageSize = is.available();

			if (imageSize > 0) {
				if (imageSize <= 10 * 1024 * 1024) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					int nRead;
					mem_pic = new byte[imageSize];
					while ((nRead = is.read(mem_pic, 0, mem_pic.length)) != -1) {
						baos.write(mem_pic, 0, nRead);
					}
					baos.flush();
					baos.close();
				} else {
					errors.put("mem_pic", "檔案必須小於 10 MB");
				}
			} else {
				mem_pic = beans.getPicture();
			}
			is.close();

			if (errors == null || errors.isEmpty()) {
				MemberBean bean = null;
				bean = memberservice.updateimf(phone, email, address, nickname,
						mem_pic, memberid);
				bean.setMpicture(Base64.encodeBase64String(bean.getPicture()));
				session.setAttribute("isLogin", bean);
				resp.sendRedirect(req.getContextPath()
						+ "/front/member/main/member.do?action=select&id="
						+ memberid);
				return;
			} else {
				req.getRequestDispatcher("/front/member/myimformation/test.jsp")
						.forward(req, resp);

			}
		}

		if ("ChangePassword".equals(action)) {

			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);
			try {
				memberservice = new MemberService();

				String oldpassword = req.getParameter("oldpassword");
				String newpassword = req.getParameter("newpassword");

				if (oldpassword == null || oldpassword.length() == 0) {
					errors.put("oldpassword", "密碼不能空白");
				}
				if (newpassword == null || newpassword.length() == 0) {
					errors.put("newpassword", "密碼不能空白");
				} else if (!newpassword.equals(req
						.getParameter("t_newpassword").trim())) {
					errors.put("newpassword", "確認密碼錯誤");

				}
				byte[] password = newpassword.getBytes();
				if (errors != null && !errors.isEmpty()) {
					req.getRequestDispatcher(
							"/front/member/main/CHGpassword.jsp").forward(req,
							resp);
					return;
				}
				HttpSession session = req.getSession();
				String account = (String) session.getAttribute("account");

				MemberBean bean = memberservice.getAccount(account);

				 
				boolean uppwd = memberservice.changePassword(account, oldpassword,
						newpassword);
				if (uppwd != false) {
					String path = req.getContextPath();
					resp.sendRedirect(path + "/index.jsp");
				} else {
					errors.put("oldpassword", "密碼錯誤");
					req.getRequestDispatcher(
							"/front/member/main/CHGpassword.jsp").forward(req,
							resp);
				}

			} catch (Exception e) {
				errors.put("Exception", e.getMessage());
				req.getRequestDispatcher("/front/member/main/CHGpassword.jsp")
						.forward(req, resp);
			}

		}

	}
}
