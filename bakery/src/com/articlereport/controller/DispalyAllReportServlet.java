package com.articlereport.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.articlereport.model.ArticleReportBean;
import com.articlereport.model.ArticleReportService;

@WebServlet("/DispalyAllReport.do")
public class DispalyAllReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request , response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleReportService ArticleReportSvc = new ArticleReportService();
		String reportStatusTemp = request.getParameter("reportStatus");
		if(reportStatusTemp == null || reportStatusTemp.isEmpty()){
			List<ArticleReportBean> beans =  ArticleReportSvc.getAllArticleReport();
			request.setAttribute("articleReport", beans);
		}else{
			int reportStatus = Integer.parseInt(reportStatusTemp);
			if(reportStatus == 1){ //未處理
				List<ArticleReportBean> beans =  ArticleReportSvc.getAllArticleReport(reportStatus);
				request.setAttribute("articleReport", beans);
			}else if(reportStatus == 2){ //已處理
				List<ArticleReportBean> beans =  ArticleReportSvc.getAllArticleReport(reportStatus);
				request.setAttribute("articleReport", beans);
			}
			request.setAttribute("reportStatus", reportStatus);
		}
		request.getRequestDispatcher("/back/article/ArticleReport.jsp").forward(request, response);
	}
}
