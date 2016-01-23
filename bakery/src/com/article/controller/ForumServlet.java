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

@WebServlet("/front/forum/Forum.do")
public class ForumServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ArticleService articleSvc = new ArticleService();

		int articleClassNo = 0;

		int totalPages = 0;

		String articleClassNoTemp = request.getParameter("ClassNo");
		if (articleClassNoTemp == null || articleClassNoTemp.isEmpty()) {
			totalPages = articleSvc.getTotalPages();
		} else {
			articleClassNo = Integer.parseInt(articleClassNoTemp);
			totalPages = articleSvc.getTotalPages(articleClassNo);
		}

		int pageNo = 0;
		String pageNoStr = request.getParameter("pageNo");
		// 沒有頁數的話
		if (pageNoStr == null) { 
			pageNo = 1;
		} else { 
			// 第二次執行
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
		List<ArticleBean> beans = articleSvc.getPageArticles(articleClassNo, pageNo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("displayArticle", beans);
		request.getRequestDispatcher("/front/article/Forum.jsp").forward(request, response);
	}
}