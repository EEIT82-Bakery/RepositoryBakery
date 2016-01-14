package com.productphoto.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.productphoto.model.ProductPhotoBean;
import com.productphoto.model.ProductPhotoService;

import sun.misc.BASE64Encoder;

@WebServlet("/ReadPhoto.do")
public class ReadPhotoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收名稱
		String productIdTemp = request.getParameter("productId");
		if (productIdTemp != null && !productIdTemp.isEmpty()) {
			ProductPhotoService dao = new ProductPhotoService();
			int productId = Integer.parseInt(productIdTemp);
			List<ProductPhotoBean> temp = dao.select(productId);
			List<ProductPhotoBean> photos = new ArrayList<>();
			for (ProductPhotoBean bean : temp) {
				bean.setPhoto_id(bean.getPhoto_id());
				bean.setProduct_id(bean.getProduct_id());
				bean.setPhoto1(Base64.encodeBase64String(bean.getPhoto())); // 4K
				photos.add(bean);
			}
			request.setAttribute("photos", photos);
			request.getRequestDispatcher("/back/product/ProductPhoto.jsp").forward(request, response);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
