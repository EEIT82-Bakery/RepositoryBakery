package com.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderBean;
import com.order.model.OrderService;
import com.orderlist.model.OrderListBean;
import com.orderlist.model.OrderListService;


@WebServlet("/OrderListServlet2.do")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String temp1 =request.getParameter("orderId");
		int orderId =Integer.parseInt(temp1);
		OrderService orderService =new OrderService();
		List<OrderBean> beans = orderService.select(orderId);
		request.setAttribute("beans", beans);
		
		OrderListService orderListService = new OrderListService();
		List<OrderListBean> bean1 = orderListService.selectProductList(orderId);
//		for(OrderListBean b : bean1){
//			System.out.println(b.getProductId());
//			System.out.println(b.getCount());
//		}
	
		request.setAttribute("bean1", bean1);
		request.getRequestDispatcher("/front/ordermember/orderlistone.jsp").forward(request, response);
	}

}
