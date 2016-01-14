package com.shoppingcart.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductBean;

@WebServlet("/Shopping.do")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// public void doGet (HttpServletRequest req, HttpServletResponse res)
	// throws ServletException, IOException {
	// doPost(req, res);
	// }
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Vector<ProductBean> buylist = (Vector<ProductBean>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		if (!action.equals("CHECKOUT")) {
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
				session.setAttribute("shoppingcart", buylist);
				String url = "/front/ShoppingCart/Cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, resp);
			} else if (action.equals("ADD")) {
				boolean match = false;
				ProductBean product = getProduct(req);
				if (buylist == null) {
					buylist = new Vector<ProductBean>();
					buylist.add(product);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProductBean productName = buylist.get(i);
						if (productName.getProductName().equals(product.getProductName())) {
							productName.setQuantity(productName.getQuantity() + product.getQuantity());
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
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductBean order = buylist.get(i);
				int price = order.getProductPrice();
				float discount=order.getDiscount();
				int quantity = order.getQuantity();
				total += (price * quantity * discount);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			session.setAttribute("shoppingcart", buylist);
			String url = "/front/ShoppingCart/CheckoutForEach.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		}
	}

	private ProductBean getProduct(HttpServletRequest req) {
		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String priceTemp = req.getParameter("price");
		String discountTemp = req.getParameter("discount");
		String productId= req.getParameter("productId");
		ProductBean productBean = new ProductBean();
		productBean.setQuantity((new Integer(quantity)).intValue());
		productBean.setProductName(name);
		productBean.setProductPrice((new Integer(priceTemp)).intValue());
		productBean.setProductId((new Integer(productId)).intValue());
		
		productBean.setDiscount((new Float(discountTemp)).floatValue());
		return productBean;
	}
}
