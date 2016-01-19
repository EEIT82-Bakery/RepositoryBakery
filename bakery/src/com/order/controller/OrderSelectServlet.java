package com.order.controller;

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

import com.order.model.OrderBean;
import com.order.model.OrderService;
import com.orderlist.model.OrderListService;

@WebServlet("/OrderSelect.do")
public class OrderSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String whichPage = request.getParameter("whichPage");
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		if("select_id".equals(action)){
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String temp1 =  request.getParameter("orderid");
				if (temp1 == null || (temp1.trim()).length() == 0) {
					errors.put("orderId","請輸入訂單編號");
				}
				
				if (!errors.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
			
				/***************************2.開始查詢資料*****************************************/
				int orderId = Integer.parseInt(temp1);
				List<OrderBean> bean = orderService.select(orderId);
				if (bean.isEmpty()) {
					errors.put("orderIdNoData","查無資料");
				}
				
				if (!errors.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("listOrder", bean); 
				request.getRequestDispatcher("/back/orderlist/orderSelectOne.jsp").forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errors.put("productNullData","無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp");
				failureView.forward(request, response);
			}
			
	}
		
		if ("update".equals(action)) { // 來自orderlist.jsp
		
			try {
				/***************************1.接收請求參數***************************************/
				Integer orderIdu = new Integer(request.getParameter("orderIdu"));
				Integer orderSatus = new Integer(request.getParameter("orderSatus"));
				/***************************2.開始刪除資料***************************************/
				
				if(orderSatus==1){
					orderSatus=2;

				}else if(orderSatus==2){
					orderSatus=3;

				}
				
				OrderBean bean =new OrderBean();
				bean.setOrderId(orderIdu);
				bean.setOrderStaus(orderSatus);
				
				OrderService orderService = new OrderService();
				
				orderService.update(bean);
				
				List<OrderBean> beans =orderService.select(orderIdu);
				request.setAttribute("aBean", beans);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp?whichPage="+whichPage); // 成功轉交 orderlist.jsp
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errors.put("deleteNo","刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp?whichPage="+whichPage);
				failureView.forward(request, response);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自orderlist.jsp
			
					try {
						/***************************1.接收請求參數***************************************/
						Integer orderIdd = new Integer(request.getParameter("orderIdd"));
						/***************************2.開始刪除資料***************************************/
						OrderListService orderListService = new OrderListService();
						orderListService.delete(orderIdd);
						
						/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
						RequestDispatcher successView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp?whichPage="+whichPage); // 成功轉交 orderlist.jsp
						successView.forward(request, response);
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errors.put("deleteNo","刪除資料失敗:"+e.getMessage());
						RequestDispatcher failureView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp?whichPage="+whichPage);
						failureView.forward(request, response);
					}
				}
		
		
	}

}
