package com.productphoto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.productphoto.model.ProductPhotoBean;
import com.productphoto.model.ProductPhotoService;


@WebServlet("/ReadPhoto.do")
public class ReadPhotoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收名稱
		String productIdTemp = request.getParameter("productId");
		if (productIdTemp != null && !productIdTemp.isEmpty()) {
			ProductPhotoService dao = new ProductPhotoService();
			int productId = Integer.parseInt(productIdTemp);
			List<ProductPhotoBean> beans = dao.select(productId);
			for (ProductPhotoBean bean : beans) {
				bean.setPhoto1(Base64.encodeBase64String(bean.getPhoto())); 
			}
			request.setAttribute("photos", beans);
			request.getRequestDispatcher("/back/product/ProductPhoto.jsp").forward(request, response);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
