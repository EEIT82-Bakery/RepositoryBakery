package com.productphoto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.productphoto.model.ProductPhotoJNDI;
import com.productphoto.model.ProductPhotoService;

import com.productphoto.model.ProductPhotoBean;

@WebServlet("/ProductPhotoServlet")
@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class InsertPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		String Update = request.getParameter("Update");
		if (Update.equals("新增")) {
			ProductPhotoBean bean = new ProductPhotoBean();
			// 接收資料
			String productIdTemp = request.getParameter("productId");
			// 驗證資料
			int productId = 0;
			if (productIdTemp != null && productIdTemp.length() != 0) {
				productId = Integer.parseInt(productIdTemp);
			} else {
				errors.put("productIdTemp", "查無此資料");
			}
			// 接收資料
			Part temp2 = request.getPart("photo");
			// 驗證資料
			if (temp2 == null) {
				errors.put("productPhoto", "圖片必須選擇");
			}
			InputStream is = temp2.getInputStream();
			byte[] buffer = new byte[(int) temp2.getSize()];
			is.read(buffer);
			is.close();
			bean.setProduct_id(productId);
			bean.setPhoto(buffer);
			ProductPhotoJNDI proPhotoJNDI = new ProductPhotoJNDI();
			proPhotoJNDI.insertPhoto(bean);
			if (errors != null) {
				response.sendRedirect(request.getContextPath()
						+ "/ReadPhoto.do?productId=" + productId);
			}
		} else if (Update.equals("刪除")) {
			String productIdTemp = request.getParameter("productId");
			int productId = Integer.parseInt(productIdTemp);
			String photoIdTemp = request.getParameter("photoid");
			int photoId = Integer.parseInt(photoIdTemp);
			ProductPhotoService service = new ProductPhotoService();
			service.delect(photoId);
			if (errors != null) {
				response.sendRedirect(request.getContextPath()
						+ "/ReadPhoto.do?productId=" + productId);
			}
		}
	}
}
