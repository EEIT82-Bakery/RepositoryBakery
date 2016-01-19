package com.rearticlereport.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rearticlereport.model.ReArticleReportBean;
import com.rearticlereport.model.ReArticleReportService;

@WebServlet("/DispalyAllReReport.do")
public class DispalyAllReReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request , response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReArticleReportService reArticleReportSvc = new ReArticleReportService();
		String reportStatusTemp = request.getParameter("reportStatus");
		if(reportStatusTemp == null || reportStatusTemp.isEmpty()){
			List<ReArticleReportBean> beans =  reArticleReportSvc.getAllReArticleReport();
			request.setAttribute("reArticleReport", beans);
		}else{
			int reportStatus = Integer.parseInt(reportStatusTemp);
			if(reportStatus == 1){ //未處理
				List<ReArticleReportBean> beans =  reArticleReportSvc.getAllReArticleReport(reportStatus);
				request.setAttribute("reArticleReport", beans);
			}else if(reportStatus == 2){ //已處理
				List<ReArticleReportBean> beans =  reArticleReportSvc.getAllReArticleReport(reportStatus);
				request.setAttribute("reArticleReport", beans);
			}
			request.setAttribute("reportStatus", reportStatus);
		}
		request.getRequestDispatcher("/back/article/ReArticleReport.jsp").forward(request, response);
	}
}
