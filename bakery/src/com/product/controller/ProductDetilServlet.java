package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductBean;
import com.product.model.ProductService;


@WebServlet("/ProductDetil.do")
public class ProductDetilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		PrintWriter outText=response.getWriter();
		String productIdTemp=request.getParameter("productId");
		int productId=Integer.parseInt(productIdTemp);
		ProductService service=new ProductService();
		ProductBean bean = service.selectId(productId);
		
	    byte[] buf = bean.getMain_photo();  // 4K buffer
        out.write(buf);
        outText.print(bean);
	}
}
