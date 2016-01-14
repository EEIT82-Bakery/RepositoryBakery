package com.article.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleService;
import com.member.model.MemberBean;
import com.rearticle.model.ReArticleService;

@WebServlet("/DeleteArticle.do")
public class DeleteArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int hidden = 1;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ArticleService articleSvc = new ArticleService();
		ReArticleService reArticleSvc = new ReArticleService();
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errors", errorMsg);

		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("isLogin");
		if (memberBean == null) {
			response.sendRedirect("/bakery/front/article/error/NotLogin.jsp");
		} else {
			int memberId = memberBean.getMember_id();
			// 接收資料(執行)
			String type = request.getParameter("type");
			// 驗證資料&轉換資料
			if ("1".equals(type)) { // 編輯文章
				// 接收資料
				String articleIdTemp = request.getParameter("articleId");
				// 驗證資料&轉換資料
				int articleId = 0;
				if (articleIdTemp != null && !articleIdTemp.isEmpty() && articleIdTemp.matches("^[0-9]+$")) {
					articleId = Integer.parseInt(articleIdTemp);

					articleSvc.updateArticleHidden(articleId , memberId , hidden);
					response.sendRedirect("/bakery/DisplayArticle.do?articleId=" + articleId);
				} else {
					response.sendRedirect(request.getContextPath() + "/front/article/error/null.jsp");
				}
			} else if ("2".equals(type)) { // 編輯回文
				
				// 接收資料
				String articleIdTemp = request.getParameter("articleId");
				// 驗證資料&轉換資料
				int articleId = 0;
				if (articleIdTemp != null && !articleIdTemp.isEmpty() && articleIdTemp.matches("^[0-9]+$")) {
					articleId = Integer.parseInt(articleIdTemp);
					int reId = 0;
					String reIdTemp = request.getParameter("reId");
					if (reIdTemp != null && !reIdTemp.isEmpty()) {
						reId = Integer.parseInt(reIdTemp);
						reArticleSvc.updateReArticleHidden(articleId, reId, memberId, hidden);
					} else {
						response.sendRedirect(request.getContextPath() + "/front/article/error/null.jsp");
					}

					// 回傳資料
					if (errorMsg == null || errorMsg.isEmpty()) {
						response.sendRedirect("/bakery/DisplayArticle.do?articleId=" + articleId);
					}
				} else {
					response.sendRedirect(request.getContextPath() + "/front/article/error/null.jsp");
				}
			} else {
				response.sendRedirect("/bakery/Forum.do");
			}
		}
	}
}