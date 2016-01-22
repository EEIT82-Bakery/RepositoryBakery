package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductBean;
import com.product.model.ProductService;

@WebServlet("/product2.controller")

public class ProductServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize=6;
		int product_type_id=1;

		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 接收資料(讀取使用者所輸入，由瀏覽器送來欄位內的資料，注意大小寫)
		String protype_Id = request.getParameter("productTypeId");
		//--------------------------------------------
		String pageStr = request.getParameter("page");
		int pageInt=Integer.parseInt(pageStr);
		//----------------------------------------------
		//例外進來若不是數字
		try {
			product_type_id=Integer.parseInt(protype_Id);
		} catch (NumberFormatException e) {
		}

		//---------------------------------------------------
		List<ProductBean> productBean= productService.selectPaging(pageInt,product_type_id);
		request.setAttribute("productList", productBean);
		request.setAttribute("page", pageInt);
				
	    //----------------------------------------------------	
		int productCount=productService.getProductCount(product_type_id);
		//總頁數=總筆數除以設定的筆數+(如果有餘數不等於0則+1頁反之+0頁)
		int pageCount = productCount/pageSize + (productCount%pageSize != 0 ?1:0);
			request.setAttribute("proTypeId",product_type_id);
			request.setAttribute("pageCount", pageCount);
			request.getRequestDispatcher("/front/product/product.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
