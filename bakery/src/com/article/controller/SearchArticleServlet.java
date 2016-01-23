package com.article.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleBean;
import com.article.model.ArticleService;

@WebServlet("/front/forum/SearchArticle.do")
public class SearchArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ArticleService articleSvc = new ArticleService();

		String articleTitle = request.getParameter("articleTitle");

		int pageNo = 1;
		int totalPages = articleSvc.getTotalPages(articleTitle);
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr == null) { // 沒有頁數的話
			pageNo = 1;
		} else { // 第二次執行
			try {
				int pageNoTemp = Integer.parseInt(pageNoStr);
				if (pageNoTemp < 0) {
					pageNo = 1;
				} else if (pageNoTemp > totalPages) {
					pageNo = totalPages;
				} else {
					pageNo = pageNoTemp;
				}
			} catch (NumberFormatException e) {
				pageNo = 1;
			}
		}
		
		List<ArticleBean> beans = articleSvc.getPageArticlesTitle(articleTitle, pageNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("articleTitle", articleTitle);
		request.setAttribute("displayArticle", beans);
		request.getRequestDispatcher("/front/article/SearchArticle.jsp").forward(request, response);
	}
}
