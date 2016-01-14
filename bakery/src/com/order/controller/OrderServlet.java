package com.order.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberBean;
import com.order.model.OrderBean;
import com.order.model.OrderService;
import com.orderlist.model.OrderListBean;
import com.orderlist.model.OrderListService;
import com.product.model.ProductBean;
import com.product.model.ProductService;



@WebServlet("/OrderServlet.do")
public class OrderServlet extends HttpServlet {
	public OrderService orderService = new OrderService();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if ("insert".equals(action)) {
		HttpSession session = request.getSession();
		Vector<OrderListBean> beans = new Vector<OrderListBean>();
		Vector<ProductBean> buylist = (Vector<ProductBean>) session.getAttribute("shoppingcart");
		for(ProductBean bean : buylist){
			OrderListBean orderListBean = new OrderListBean();
			orderListBean.setProductId(bean.getProductId());
			orderListBean.setCount( bean.getQuantity());
			beans.add(orderListBean);
		}		
		String orderName = request.getParameter("orderName");
		String orderPhone =request.getParameter("orderPhone");
		String orderAddress =request.getParameter("orderAddress");
		String temp1 =request.getParameter("totalMmount");
		String count = request.getParameter("amount");
		String temp2 =request.getParameter("shipDate");

		//取得會員ID
		MemberBean bean =(MemberBean) session.getAttribute("isLogin");
		int memberId = bean.getMember_id();

		
		if(orderName== null || orderName.length() == 0){
			errors.put("orderName", "請輸入收件人姓名");
		}
		
		if (orderPhone == null || orderPhone.trim().length() == 0) {
			errors.put("orderPhone", "請輸入手機號碼");
		} else if (!orderPhone.matches("^09\\d{2}-?\\d{3}-?\\d{3}$")) {
			errors.put("orderPhone", "且確認電話格式");
		}
		
		if (orderAddress == null || orderAddress.length() == 0)
		{
			errors.put("orderAddress", "請輸入地址");
		}

		int totalAmount = Integer.parseInt(temp1);
		
		java.util.Date shipDate = null;
		if (temp2 != null && temp2.trim().length() != 0) {
			shipDate = orderService.convertDate(temp2);
		} else {
			errors.put("orderDate", "orderDate必須是日期必且擁有yyyy-MM-dd的格式");
		}
			
		
		if (errors != null && !errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/front/ShoppingCart/CheckoutForEach.jsp").forward(request, response);
		} else {
			OrderBean obean = new OrderBean();
			
			obean.setOrderName(orderName);
			obean.setOrderPhone(orderPhone);
			obean.setOrderAddress(orderAddress);
			obean.setTotalAmount(totalAmount);
			obean.setShipDate(shipDate);
			obean.setMemberId(memberId);
			
			orderService.insertOrder(obean, beans);
			session.removeAttribute("shoppingcart");
			session.removeAttribute("amount");
			response.sendRedirect(request.getContextPath() + "/product2.controller?page=1");
		}		
		}
		
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
					OrderService orderService = new OrderService();
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
					request.setAttribute("aBean", bean); 
					String url = "/back/orderlist/orderlist.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交orderlist.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errors.put("productNullData","無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp");
					failureView.forward(request, response);
				}
				
		}


		if ("delete".equals(action)) { // 來自orderlist.jsp
					
					try {
						/***************************1.接收請求參數***************************************/
						Integer orderIdd = new Integer(request.getParameter("orderIdd"));
						System.out.println(orderIdd);
						/***************************2.開始刪除資料***************************************/
						OrderListService orderListService = new OrderListService();
						orderListService.delete(orderIdd);
						
						/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
						String url = "/back/orderlist/orderlist.jsp";
						RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 orderlist.jsp
						successView.forward(request, response);
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e) {
						errors.put("deleteNo","刪除資料失敗:"+e.getMessage());
						RequestDispatcher failureView = request.getRequestDispatcher("/back/orderlist/orderlist.jsp");
						failureView.forward(request, response);
					}
				}
				
				//----------
		}	
			
		}
	


