package com.product.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductBean;
import com.product.model.ProductJNDIDAO;

@WebServlet("/DBGifReader.do")
public class DBGifReader extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			String productId = req.getParameter("productId");
			ProductJNDIDAO dao = new ProductJNDIDAO();
			ProductBean bean = dao.selectPhoto(new Integer(productId));
			byte[] buf = bean.getMain_photo(); // 4K buffer
			out.write(buf);
			out.close();

		} catch (Exception e) {
			String fileName = getServletContext().getRealPath(
					"/front/HtmlData/images/1.jpg");
			FileInputStream in = new FileInputStream(fileName);
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
			out.close();
		}
	}

}
