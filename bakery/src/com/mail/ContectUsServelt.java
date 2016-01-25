package com.mail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContectUsServelt.do")
public class ContectUsServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContectUsServelt() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String customer = request.getParameter("name");
		String mail = request.getParameter("email");
		String tel = request.getParameter("Text");    
		String text = request.getParameter("citus");
		String from = mail;
		String to = "q22488757@gmail.com";
		String subject = "您好,我是" + customer;
		String textx = "聯絡人:"+customer +"<br/><p>Email:"+ mail+"<br/><p>連絡電話:"+tel +"<br/><p>內容:"+text;

		List<String> attachment = Arrays.asList(
				   new String[]{
						   //放圖片
						   });;
		JavaMailUtil  util = new JavaMailUtil(from, to,subject,textx,attachment);
		if (util.send()){
			   System.out.println("發信成功");
			} else {
			   System.out.println("發信失敗");
			}
		request.getRequestDispatcher("/index.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
