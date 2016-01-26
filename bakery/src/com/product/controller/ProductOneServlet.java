package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductBean;
import com.product.model.ProductService;


@WebServlet("/ProductOneServlet.do")
public class ProductOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String temp1= request.getParameter("productId");
		int productId =Integer.parseInt(temp1);
		ProductBean bean=productService.selectId(productId);
		request.setAttribute("bean", bean);
		System.out.println(bean);
		request.getRequestDispatcher("/front/product/productone.jsp").forward(request, response);
	}

}
