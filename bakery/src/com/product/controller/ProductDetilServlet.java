package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import com.product.model.ProductBean;
import com.product.model.ProductService;

@WebServlet("/ProductDetil.do")
public class ProductDetilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String productIdTemp = request.getParameter("productId");
		int productId = Integer.parseInt(productIdTemp);
		ProductService service = new ProductService();
		ProductBean bean = service.selectId(productId);
		bean.setMain_photo1(Base64.encodeBase64String(bean.getMain_photo()));
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("ProductId", bean.getProductId());
			jsonObj.put("ProductName", bean.getProductName());
			jsonObj.put("Picture", bean.getMain_photo1());
			jsonObj.put("Price", bean.getProductPrice());
			jsonObj.put("Status", bean.getProductStatus());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(jsonObj.toString());
		out.close();
	}
}
