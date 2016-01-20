package com.articlereport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.articlereport.model.ArticleReportService;
import com.rearticle.model.ReArticleService;
import com.rearticlereport.model.ReArticleReportService;

@WebServlet("/Blockade.do")
public class BlockadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String articleIdTemp = request.getParameter("articleId");
		String IdTemp = request.getParameter("Id");
		
		if (articleIdTemp != null && !articleIdTemp.isEmpty() && articleIdTemp.matches("^[0-9]+$")) {
			int articleId = Integer.parseInt(articleIdTemp);
			ArticleService articleSvc = new ArticleService();
			ArticleReportService articleReportSvc = new ArticleReportService();
			articleSvc.updateArticleHidden(articleId, 2);
			articleReportSvc.updateArticleReportStatus(articleId, 2);

			response.sendRedirect(request.getContextPath() + "/DispalyAllReport.do");
		} else if (IdTemp != null && !IdTemp.isEmpty()) {
			int Id = Integer.parseInt(IdTemp);
			ReArticleService reArticleSvc = new ReArticleService();
			ReArticleReportService reArticleReportSvc = new ReArticleReportService();
			reArticleSvc.updateReArticleHidden(Id, 2);
			reArticleReportSvc.updateReArticleReportStatus(Id, 2);

			response.sendRedirect(request.getContextPath() + "/DispalyAllReReport.do");
		}
	}
}
