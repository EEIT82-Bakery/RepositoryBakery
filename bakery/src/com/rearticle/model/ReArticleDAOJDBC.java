package com.rearticle.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReArticleDAOJDBC implements ReArticleDAO_interface {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=bakeryDB";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa123456";
	@Override
	public int insertReArticle(int memberId, String reContent, int articleId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(int reArticleId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean updateReArticle(String reContent, int reId, int articleId, int memberId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<ReArticleBean> getAllReArticle(int articleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReArticleBean getLastReArticle(int articleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReArticleBean getOneReArticle(int articleId, int reId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateReArticleHidden(int articleId, int reId, int memberId, int hidden) {
		// TODO Auto-generated method stub
		
	}


}
