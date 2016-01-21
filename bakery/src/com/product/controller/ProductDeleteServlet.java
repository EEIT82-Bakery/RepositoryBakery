package com.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;

@WebServlet("/ProductDeleteServlet.do")
public class ProductDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		/*************************** 1.接收請求參數 ****************************************/
		String action = request.getParameter("action");
		String whichPage = request.getParameter("whichPage");
		/*************************** 2.開始查詢資料 ****************************************/
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer productId = new Integer(request.getParameter("productId"));
				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productService = new ProductService();
				productService.delete(productId);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				RequestDispatcher successView = request
						.getRequestDispatcher("/back/product/ProductSelectAll.jsp?whichPage="
								+ whichPage); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errors.put("deleteNo", "刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back/product/ProductSelectAll.jsp?whichPage="
								+ whichPage);
				failureView.forward(request, response);
			}
		}
	}
}
