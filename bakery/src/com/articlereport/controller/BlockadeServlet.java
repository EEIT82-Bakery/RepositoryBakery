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
		if (articleIdTemp != null && !articleIdTemp.isEmpty()
				&& articleIdTemp.matches("^[0-9]+$")) {
			int articleId = Integer.parseInt(articleIdTemp);
			// 判斷有無回文ID
			String reIdTemp = request.getParameter("reId");
			if (reIdTemp == null || reIdTemp.isEmpty()) {// 沒有的話執行封鎖文章
				ArticleService articleSvc = new ArticleService();
				ArticleReportService articleReportSvc = new ArticleReportService();
				articleSvc.updateArticleHidden(articleId, 2);
				articleReportSvc.updateArticleReportStatus(articleId, 2);

				response.sendRedirect(request.getContextPath() + "/DispalyAllReport.do");
			} else {// 有的話執行封鎖回文
				int reId = Integer.parseInt(reIdTemp);
				ReArticleService reArticleSvc = new ReArticleService();
				ReArticleReportService reArticleReportSvc = new ReArticleReportService();
				reArticleSvc.updateReArticleHidden(articleId, reId, 2);
				reArticleReportSvc.updateReArticleReportStatus(articleId, reId,	2);
				
				response.sendRedirect(request.getContextPath() + "/DispalyAllReport.do");
			}
		}
	}
}
