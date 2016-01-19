package com.order.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product.model.ProductBean;
import com.product.model.ProductJNDIDAO;


 @WebServlet("/OrderListReaderServlet.do")
public class OrderListReaderServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                               throws ServletException, IOException {
    
    res.setContentType("image/gif");
    ServletOutputStream out = res.getOutputStream();
    
    try {
        String productId = req.getParameter("productId");
        System.out.println(productId);
        ProductJNDIDAO dao = new ProductJNDIDAO();
        ProductBean bean = dao.selectPhoto(new Integer(productId));
//        System.out.println(bean);
        byte[] buf = bean.getMain_photo();  // 4K buffer
        out.write(buf);
        out.close();
    } catch(Exception e) {
       String fileName=getServletContext().getRealPath("/front/HtmlData/images/1.jpg");
       FileInputStream in = new FileInputStream(fileName);
       byte[] buf = new byte[in.available()]; 
       in.read(buf);
       out.write(buf);
       out.close();
    }
  }

}

  
