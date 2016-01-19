package com.articlereport.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.articlereport.model.ArticleReportService;
import com.member.model.MemberBean;
import com.rearticlereport.model.ReArticleReportService;

@WebServlet("/InsertReport.do")
public class InsertReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean) session.getAttribute("isLogin");
		if (memberBean == null) {
			out.print("請先登入後，才能檢舉。");
			out.close();
		} else {
			int memberId = memberBean.getMember_id();
			String articleIdTemp = request.getParameter("articleId");
			if (articleIdTemp != null && !articleIdTemp.isEmpty()
					&& articleIdTemp.matches("^[0-9]+$")) {
				int articleId = Integer.parseInt(articleIdTemp);
				String reportMsg = request.getParameter("reportMsg");
				if (reportMsg != null && !reportMsg.isEmpty()) {
					//判斷有無回文ID
					String reIdTemp = request.getParameter("reId");
					if (reIdTemp == null || reIdTemp.isEmpty()) {//沒有的話執行檢舉文章
						
						ArticleReportService articleReportSvc = new ArticleReportService();
						if (!articleReportSvc.isReport(memberId, articleId)) {
							articleReportSvc.insertArticleReport(memberId,
									articleId, reportMsg);
							out.print("已檢舉成功!");
							out.close();
						} else {
							out.print("您已檢舉過!");
							out.close();
						}
					} else {//有的話執行檢舉回文
						int reId = Integer.parseInt(reIdTemp);
						ReArticleReportService reArticleReportSvc = new ReArticleReportService();
						if (!reArticleReportSvc.isReReport(memberId, articleId, reId)) {
							reArticleReportSvc.insertReArticleReport(memberId,
									articleId, reId, reportMsg);
							out.print("已檢舉成功!");
							out.close();
						} else {
							out.print("您已檢舉過!");
							out.close();
						}
					}
				} else {
					out.print("請輸入檢舉內容!");
					out.close();
				}
			}
		}
	}
}
