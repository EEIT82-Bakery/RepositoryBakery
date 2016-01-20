package com.articlecollection.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.articlecollection.model.ArticleCollectionService;
import com.member.model.MemberBean;

@WebServlet("/InsertArticleCollection.do")
public class InsertArticleCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("isLogin");
		if (memberBean == null) {
			out.print("請先登入後，才能收藏。");
			out.close();
		} else {
			int memberId = memberBean.getMember_id();
			String articleIdTemp = request.getParameter("articleId");
			if (articleIdTemp != null && !articleIdTemp.isEmpty() && articleIdTemp.matches("^[0-9]+$")) {
				int articleId = Integer.parseInt(articleIdTemp);
				ArticleCollectionService articleCollectionSvc = new ArticleCollectionService();
				if (!articleCollectionSvc.getHaveCollection(memberId, articleId)) {
					articleCollectionSvc.addArticleCollection(memberId, articleId);
					out.print("已收藏成功!");
					out.close();
				} else {
					out.print("您已收藏過!");
					out.close();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
}
