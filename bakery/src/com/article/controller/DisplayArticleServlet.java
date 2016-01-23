package com.article.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleBean;
import com.article.model.ArticleService;
import com.rearticle.model.ReArticleBean;
import com.rearticle.model.ReArticleService;
@WebServlet("/front/forum/DisplayArticle.do")
public class DisplayArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		ArticleService articleSvc = new ArticleService();
		ReArticleService reArticleSvc = new ReArticleService();
		// 接收資料
		String articleIdTemp = request.getParameter("articleId");
		// 驗證資料
		ArticleBean bean = null;
		if (articleIdTemp != null && !articleIdTemp.isEmpty() && articleIdTemp.matches("^[0-9]+$")) {
			// 轉換資料
			int articleId = Integer.parseInt(articleIdTemp.trim());
			List<Integer> articleIdList = (List<Integer>) session.getAttribute("articleIdList");
			if (articleIdList == null || articleIdList.isEmpty()) {
				articleIdList = new ArrayList<Integer>();
				session.setAttribute("articleIdList", articleIdList);
			}
			if (!articleSvc.CheckArticleId(articleId, articleIdList)) {
				articleIdList.add(articleId);
				articleSvc.updateBrowserCount(articleId);
			}

			bean = articleSvc.getArticle(articleId);
			if(bean != null){
				List<ReArticleBean> beans = reArticleSvc.getAllReArticle(articleId);
				request.setAttribute("Article", bean);
				request.setAttribute("reArticle", beans);
				request.getRequestDispatcher("/front/article/DisplayArticle.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/front/article/error/null.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/front/article/error/null.jsp");
		}

	}
}