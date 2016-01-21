package com.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.MemberService;


@MultipartConfig
@WebServlet("/front/member/regitser/regis.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MessageDigest messageDigest;

	public RegisterServlet() {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
		private static final String DADA = "/front/member/images/picture.jpg";
		private static final int statu = 2;
		private static final int point = 0;
		private static final int order = 0;
		private static final String kanban = null;
		private MemberService memberservice = new MemberService();
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/plain; charset=utf-8");
		HttpSession session = req.getSession();
		Map<String, String> errorMessage = new HashMap<String, String>();
		req.setAttribute("ErrorMsg", errorMessage);
		Map<String, String> msgOK = new HashMap<String, String>();
		session.setAttribute("ok", msgOK);
		String account = req.getParameter("m_account");
		if (account == null || account.isEmpty() || account.trim().length() == 0) {
			errorMessage.put("x_account", "帳號欄必須輸入");
		}
//			else if (accountlist.contains(account)) {
//			errorMessage.put("x_account", "帳號重複");
//		}
		String password1 = req.getParameter("m_password").trim().toLowerCase();
		if (password1 == null || password1.isEmpty() || password1.length() == 0) {
			errorMessage.put("x_password", "密碼欄必須輸入");
		} else if (!password1.equals(req.getParameter("m_pwd").trim())) {
			errorMessage.put("x_password", "確認密碼錯誤");
		}
		byte[] password = password1.getBytes();
		password = messageDigest.digest(password);	
		String username = req.getParameter("m_name").trim();
		if (username == null || username.length() == 0) {
			errorMessage.put("x_m_name", "名子不能為空白");
		} else if (!username.matches("^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$")) {
			errorMessage.put("x_m_name", "名子必須為中英文且2到10個字");
		}
		String sex = req.getParameter("m_gender");
		if (sex == null || sex.length() == 0) {
			errorMessage.put("x_m_gender", "請選擇性別");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birth = null;
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		String birthTemp = year + "-" + month + "-" + day;
		try {
			birth = df.parse(birthTemp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String email = req.getParameter("m_email").trim().toLowerCase();
		if (email == null || email.length() == 0) {
			errorMessage.put("x_m_email", "信箱: 請勿空白");
		} else if (!email.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$"))
		{
			errorMessage.put("x_m_email", "非有效的e-mail格式");
		}
		// else if (xemail.contains(email)) {
		// errorMessage.put("x_m_email", " 已被使用的信箱");
		// }
		String phone = req.getParameter("phone");
		if (phone == null || phone.trim().length() == 0) {
			errorMessage.put("x_e_phone", "請輸入手機號碼");
		} else if (!phone.matches("^09\\d{2}-?\\d{3}-?\\d{3}$")) {
			errorMessage.put("x_e_phone", "且確認電話格式");
		}
		String address = req.getParameter("m_address").trim();
		if (address == null || address.length() == 0)
		{
			errorMessage.put("m_m", "請輸入地址");
		}
		Timestamp last_date = new Timestamp(System.currentTimeMillis());
		Part part = req.getPart("m_pic");
		InputStream is = part.getInputStream();
		int imageSize = is.available();
		if (imageSize <= 0) {
			is = this.getServletContext().getResourceAsStream(DADA);
			imageSize = is.available();
		}
		byte[] picture = null;
		if (imageSize <= 10 * 1024 * 1024) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int nRead;
			picture = new byte[imageSize];
			while ((nRead = is.read(picture, 0, picture.length)) != -1) {
				baos.write(picture, 0, nRead);
			}
			baos.flush();
			baos.close();
		} else {
			errorMessage.put("m_pic", "檔案過大,檔案必須小於 10 MB");
		}
		is.close();
		String nickname = req.getParameter("nickname").trim();
		if (nickname == null || nickname.length() == 0) {
			errorMessage.put("m_nick", "暱稱為必填欄位，不得為空白");
		}
		if (!errorMessage.isEmpty())			
		{
			req.getRequestDispatcher("/front/member/register/regis.jsp").forward(req, resp);
			return;
		}
		memberservice = new MemberService();
		memberservice.addMember(account, password, username, sex, birth, email, phone, address, last_date, picture, statu, point, order, nickname,kanban);
		String path = req.getContextPath();
		resp.sendRedirect(resp.encodeRedirectURL(path + "/index.jsp"));
		return;
	}
}

// }
