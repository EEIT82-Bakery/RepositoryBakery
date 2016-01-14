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

import com.article.model.ArticleBean;
import com.article.model.ArticleService;
import com.member.model.MemberBean;
import com.rearticle.model.ReArticleBean;
import com.rearticle.model.ReArticleService;

@WebServlet("/InsertArticle.do")
public class InsertArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			if ("1".equals(type)) { // 發表文章
				// 接收資料(種類編號)
				String articleClassNoTemp = request.getParameter("articleClassNo");
				// 驗證資料&轉換資料
				int articleClassNo = 0;
				if (articleClassNoTemp == null || articleClassNoTemp.isEmpty() || articleClassNoTemp.equals("請選擇種類")) {
					errorMsg.put("articleClassNo", "請選擇種類");
				} else {
					articleClassNo = Integer.parseInt(articleClassNoTemp);
				}

				// 接收資料(標題)
				String articleTitle = request.getParameter("articleTitle").trim();
				// 驗證資料&轉換資料
				if (articleTitle == null || articleTitle.isEmpty()) {
					errorMsg.put("articleTitle", "請輸入標題");
				}

				// 接收資料(內容)
				String content = request.getParameter("content").trim();

				// 驗證資料&轉換資料
				if (content == null || content.isEmpty()) {
					errorMsg.put("content", "請輸入標題");
				}

				if (errorMsg == null || errorMsg.isEmpty()) {
					articleSvc.insertArticle(memberId, articleClassNo, articleTitle, content);
					response.sendRedirect(
							request.getContextPath() + "/Forum.do");
				} else {
					request.getRequestDispatcher("/front/article/InsertArticle.jsp?type=1").forward(request, response);
				}

			} else if ("2".equals(type)) { // 回覆文章
				// 接收資料
				String articleIdTemp = request.getParameter("articleId");
				// 驗證資料&轉換資料
				int articleId = 0;
				if (articleIdTemp != null && !articleIdTemp.isEmpty() && articleIdTemp.matches("^[0-9]+$")) {
					articleId = Integer.parseInt(articleIdTemp);
					String articleTitle = articleSvc.getArticleTitle(articleId);
					request.setAttribute("articleTitle", articleTitle);

					// 接收資料(回文內容)
					String reContent = request.getParameter("content");
					// 驗證資料&轉換資料
					if (reContent == null || reContent.isEmpty()) {
						errorMsg.put("reContent", "請輸入內容");
					}

					// 回傳資料
					if (errorMsg == null || errorMsg.isEmpty()) {
						reContent.trim();
						reArticleSvc.insertReArticle(memberId, reContent, articleId);
						response.sendRedirect("/bakery/DisplayArticle.do?articleId=" + articleId);
					} else {
						request.getRequestDispatcher("/front/article/InsertArticle.jsp").forward(request, response);
					}
				} else {
					response.sendRedirect(request.getContextPath() + "/front/article/error/null.jsp");
				}

			} else if ("3".equals(type)) { // 編輯文章
				// 接收資料
				String articleIdTemp = request.getParameter("articleId");
				// 驗證資料&轉換資料
				int articleId = 0;
				if (articleIdTemp != null && !articleIdTemp.isEmpty() && articleIdTemp.matches("^[0-9]+$")) {
					articleId = Integer.parseInt(articleIdTemp);
					ArticleBean bean = articleSvc.getArticle(articleId);
					request.setAttribute("articleBean", bean);

					// 接收資料(種類編號)
					String articleClassNoTemp = request.getParameter("articleClassNo");
					// 驗證資料&轉換資料
					int articleClassNo = 0;
					if (articleClassNoTemp == null || articleClassNoTemp.isEmpty()
							|| articleClassNoTemp.equals("請選擇種類")) {
						errorMsg.put("articleClassNo", "請選擇種類");
					} else {
						articleClassNo = Integer.parseInt(articleClassNoTemp);
					}

					// 接收資料(標題)
					String articleTitle = request.getParameter("articleTitle");
					// 驗證資料&轉換資料
					if (articleTitle == null || articleTitle.isEmpty()) {
						errorMsg.put("articleTitle", "請輸入標題");
					}

					// 接收資料(內容)
					String content = request.getParameter("content");

					// 驗證資料&轉換資料
					if (content == null || content.isEmpty()) {
						errorMsg.put("content", "請輸入標題");
					}

					if (errorMsg == null || errorMsg.isEmpty()) {
						articleSvc.updateArticle(articleClassNo, articleTitle, content, articleId, memberId);
						response.sendRedirect("/bakery/DisplayArticle.do?articleId=" + articleId);
					} else {
						request.getRequestDispatcher("/front/article/InsertArticle.jsp").forward(request, response);
					}
				} else {
					response.sendRedirect(request.getContextPath() + "/front/article/error/null.jsp");
				}

			} else if ("4".equals(type)) { // 編輯回文
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
						ReArticleBean bean = reArticleSvc.getOneReArticle(articleId , reId);
						request.setAttribute("reArticleBean", bean);
					} else {
						errorMsg.put("reId", "編號不能為空");
					}

					// 接收資料(回文內容)
					String reContent = request.getParameter("content");
					// 驗證資料&轉換資料
					if (reContent == null || reContent.isEmpty()) {
						errorMsg.put("reContent", "請輸入內容");
					}

					// 回傳資料
					if (errorMsg == null || errorMsg.isEmpty()) {
						reArticleSvc.updateReArticle(reContent, reId, articleId, memberId);
						response.sendRedirect("/bakery/DisplayArticle.do?articleId=" + articleId);
					} else {
						request.getRequestDispatcher("/front/article/InsertArticle.jsp").forward(request, response);
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
