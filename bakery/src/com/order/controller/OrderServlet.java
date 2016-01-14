package com.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.orderlist.model.OrderListBean;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<OrderListBean> beans = new ArrayList<OrderListBean>();
		for (int i = 1; i <= 10; i++) {
			OrderListBean bean = new OrderListBean();
			bean.setProductId(i);
			beans.add(bean);
		}

		int orderName = 1;
		OrderService OrderSvc = new OrderService();
		OrderSvc.insertOrder(orderName, beans);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
