package com.shoppingcart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberBean;
import com.product.model.ProductBean;

@WebServlet("/Shopping.do")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		MemberBean mb = (MemberBean) session.getAttribute("isLogin");
		Vector<ProductBean> buylist = (Vector<ProductBean>) session.getAttribute("shoppingcart");
		String action = request.getParameter("action");
		if (!action.equals("CHECKOUT")) {
			if (action.equals("DELETE")) {
				String del = request.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
				JSONArray jsonArray = new JSONArray();
				try {
					for (ProductBean bean : buylist) {
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("ProductId", bean.getProductId());
						jsonObj.put("ProductName", bean.getProductName());
						jsonObj.put("Discount", (bean.getDiscount()));
						jsonObj.put("Price", bean.getProductPrice());
						jsonObj.put("Quantity", bean.getQuantity());
						jsonArray.put(jsonObj);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.print(jsonArray.toString());
				out.close();
				session.setAttribute("shoppingcart", buylist);
			} else if (action.equals("ADD")) {
				boolean match = false;
				ProductBean product = getProduct(request);
				if (buylist == null) {
					buylist = new Vector<ProductBean>();
					buylist.add(product);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProductBean productName = buylist.get(i);
						if (productName.getProductName().equals(
								product.getProductName())) {
							productName.setQuantity(productName.getQuantity()
									+ product.getQuantity());
							buylist.setElementAt(productName, i);
							match = true;
						} // end of if name matches
					} // end of for
					if (!match)
						buylist.add(product);
				}
				session.setAttribute("shoppingcart", buylist);
			}
		} else if (action.equals("CHECKOUT")) {
			if (mb == null) {
				response.sendRedirect(request.getContextPath()
						+ "/front/article/error/NotLogin.jsp");
			} else if (buylist == null) {
				response.sendRedirect(request.getContextPath()
						+ "/front/article/error/NotShopping.jsp");
			} else {
				int total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					ProductBean order = buylist.get(i);
					int price = order.getProductPrice();
					String discount = order.getDiscount();
					float f = new Float(discount);
					int quantity = order.getQuantity();
					total += (price * quantity * f);
				}
				String amount = String.valueOf(total);
				session.setAttribute("amount", amount);
				session.setAttribute("shoppingcart", buylist);
				String url = "/front/ShoppingCart/CheckoutForEach.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
		}
	}

	private ProductBean getProduct(HttpServletRequest request) {
		String quantity = request.getParameter("quantity");
		String name = request.getParameter("name");
		String priceTemp = request.getParameter("price");
		String discountTemp = request.getParameter("discount");
		String productId = request.getParameter("productId");
		ProductBean productBean = new ProductBean();
		productBean.setQuantity((new Integer(quantity)).intValue());
		productBean.setProductName(name);
		productBean.setProductPrice((new Integer(priceTemp)).intValue());
		productBean.setProductId((new Integer(productId)).intValue());
		productBean.setDiscount(discountTemp);
		return productBean;
	}
}
