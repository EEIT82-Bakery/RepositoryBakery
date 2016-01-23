package com.order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberBean;
import com.member.model.MemberDAOHibernate;
import com.order.model.OrderBean;
import com.order.model.OrderService;
import com.orderlist.model.OrderListBean;
import com.product.model.ProductBean;

@WebServlet("/OrderServlet.do")
public class OrderServlet extends HttpServlet {
	public OrderService orderService = new OrderService();
	public MemberDAOHibernate memberDAOHibernate = new MemberDAOHibernate();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		String action = request.getParameter("action");

		if ("insert".equals(action)) {
			HttpSession session = request.getSession();
			Vector<OrderListBean> beans = new Vector<OrderListBean>();
			Vector<ProductBean> buylist = (Vector<ProductBean>) session
					.getAttribute("shoppingcart");
			for (ProductBean bean : buylist) {
				OrderListBean orderListBean = new OrderListBean();
				orderListBean.setProductId(bean.getProductId());
				orderListBean.setQuantity(bean.getQuantity());
				beans.add(orderListBean);
			}
			String orderName = request.getParameter("orderName");
			String orderPhone = request.getParameter("orderPhone");
			String orderAddress = request.getParameter("orderAddress");
			String temp1 = request.getParameter("totalMmount");
			String temp2 = request.getParameter("shipDate");

			// 取得會員ID
			MemberBean bean = (MemberBean) session.getAttribute("isLogin");
			int memberId = bean.getMember_id();
			MemberBean mb = memberDAOHibernate.getOne(memberId);
			int point1 = mb.getPoint();

			if (orderName == null || orderName.length() == 0) {
				errors.put("orderName", "請輸入收件人姓名");
			}

			if (orderPhone == null || orderPhone.trim().length() == 0) {
				errors.put("orderPhone", "請輸入手機號碼");
			} else if (!orderPhone.matches("^09\\d{2}-?\\d{3}-?\\d{3}$")) {
				errors.put("orderPhone", "且確認電話格式");
			}

			if (orderAddress == null || orderAddress.length() == 0) {
				errors.put("orderAddress", "請輸入地址");
			}

			int point = (Integer.parseInt(temp1)) / 10;

			int totalAmount = Integer.parseInt(temp1);

			java.util.Date shipDate = null;
			if (temp2 != null && temp2.trim().length() != 0) {
				shipDate = orderService.convertDate(temp2);
			} else {
				errors.put("orderDate", "orderDate必須是日期必且擁有yyyy-MM-dd的格式");
			}

			if (errors != null && !errors.isEmpty()) {
				request.setAttribute("errors", errors);
				request.getRequestDispatcher(
						"/front/ShoppingCart/CheckoutForEach.jsp").forward(
						request, response);
			} else {

				memberDAOHibernate.updatepoint(memberId, point + point1);

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
				response.sendRedirect(request.getContextPath()
						+ "/product2.controller?page=1");
			}
		}
	}

}
