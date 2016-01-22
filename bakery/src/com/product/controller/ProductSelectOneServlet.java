package com.product.controller;

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

import com.product.model.ProductBean;
import com.product.model.ProductService;

@WebServlet("/ProductSelectOneServlet.do")
public class ProductSelectOneServlet extends HttpServlet {

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

		/*************************** 2.開始查詢資料 ****************************************/
		// 驗證資料

		if ("getOne_For_Display".equals(action)) {
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String productName = request.getParameter("productName");
				if (productName == null || (productName.trim()).length() == 0) {
					errors.put("productName", "請輸入產品名稱");
				}
				if (!errors.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back/product/ProductSelectAll.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService productService = new ProductService();
				List<ProductBean> bean = productService.select(productName);
				if (bean.isEmpty()) {
					errors.put("productNoData", "查無資料");
				}
				if (!errors.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back/product/ProductSelectAll.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("aBean", bean); // 資料庫取出的empVO物件,存入req
				String url = "/back/product/ProductSelectOne.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errors.put("productNullData", "無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back/product/ProductSelectAll.jsp");
				failureView.forward(request, response);
			}

			if ("delete".equals(action)) { // 來自listAllEmp.jsp

				try {
					/*************************** 1.接收請求參數 ***************************************/
					Integer productId = new Integer(
							request.getParameter("productId"));

					/*************************** 2.開始刪除資料 ***************************************/
					ProductService productService = new ProductService();
					productService.delete(productId);

					/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
					String url = "/back/product/ProductSelectAll.jsp";
					RequestDispatcher successView = request
							.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(request, response);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errors.put("deleteNo", "刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back/product/ProductSelectAll.jsp");
					failureView.forward(request, response);
				}
			}
		}
	}
}
