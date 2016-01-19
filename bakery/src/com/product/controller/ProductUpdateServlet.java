package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductBean;
import com.product.model.ProductService;

@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024
		* 1024 * 500 * 5)

@WebServlet("/ProductUpdateServlet.do")
public class ProductUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		/*************************** * 1.接收請求參數 ****************************************/
		String action = request.getParameter("action");
		
		if ("getOne_For_Update".equals(action)) { // // 來自listAllEmp.jsp的請求
			
			String productId=request.getParameter("productId");
			String productName=request.getParameter("productName");
			String productStatus=request.getParameter("productStatus");
			String productPrice=request.getParameter("productPrice");
			String discount=request.getParameter("discount");
			String productDate=request.getParameter("productDate");
			request.setAttribute("productName", productName);
			request.setAttribute("productStatus", productStatus);
			request.setAttribute("productPrice", productPrice);
			request.setAttribute("discount", discount);
			request.setAttribute("productDate", productDate);

			request.getRequestDispatcher("/back/product/ProductUpdate.jsp").forward(request, response);
			
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);
				/***************************
				 * 1.接收請求參數
				 ****************************************/
				String temp6= request.getParameter("productId");
				int productId = Integer.parseInt(temp6);
				
				String pname = request.getParameter("productName");	
				
					if (pname == null || pname.length() == 0) {
						errors.put("productName", "請輸入產品名稱以便執行" );
					}

					String pstatus = request.getParameter("productStatus");

					if (pstatus == null || pstatus.trim().length() == 0) {
						errors.put("productStatus", "產品描述必須輸入");
					}

					int price = 0;
					String temp1 = request.getParameter("productPrice");
					
					if (temp1 == null || temp1.trim().length() == 0){
						errors.put("productPrice", "金額不能為空白");
					}else if(!temp1.matches("^/+?[1-9][0-9]*$")){
						errors.put("productPrice", "金額必須為正整數");
					} else{
						price = Integer.parseInt(temp1);
					}

					Part temp2 = request.getPart("mainPhoto");
					if (temp2 == null) {
						errors.put("mainPhoto", "圖片必須選擇");
					}
					InputStream fi = temp2.getInputStream();
					byte[] buffer = new byte[(int) temp2.getSize()];
					fi.read(buffer);
					fi.close();

					
					String discount = request.getParameter("discount");
					if (discount == null || (discount.trim()).length() == 0) {
						errors.put("discount", "請輸入折扣");
					} else if(!discount.matches("^[0-9]+(.[0-9]{1})?$")){
						errors.put("discount", "折扣必須是0.1~0.9之間");
					}
					
					String temp4 = request.getParameter("productDate");
					java.util.Date product_date = null;
					if (temp4 != null && temp4.length() != 0) {
						product_date = ProductBean.convertDate(temp4);
					} else {
						errors.put("productDate", "product_date必須是日期必且擁有yyyy-MM-dd的格式");
					}

					String temp5 = request.getParameter("proTypeId").trim();
					int productTypeId = 0;
					if (temp5 == null || temp5.length() == 0 || temp5.equals("0")) {
						errors.put("proTypeId", "產品種類必須選擇");
					} else {
						productTypeId = Integer.parseInt(temp5);
					}

							
					if (errors != null && !errors.isEmpty()) {
						request.setAttribute("errorMsg", errors);
						request.getRequestDispatcher("/back/product/ProductUpdate.jsp").forward(request, response);
					}else{
						ProductBean bean = new ProductBean();
						bean.setProductName(pname);
						bean.setProductStatus(pstatus);
						bean.setProductPrice(price);
						bean.setMain_photo(buffer);
						bean.setDiscount(discount);
						bean.setProductDate(product_date);
						bean.setProductTypeId(productTypeId);
						bean.setProductId(productId);
						productService.update(bean);
						response.sendRedirect(request.getContextPath() + "/back/product/ProductSelectAll.jsp");
					}
				}	
				
		}
		
}

