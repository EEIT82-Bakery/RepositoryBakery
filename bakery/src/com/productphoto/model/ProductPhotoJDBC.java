package com.productphoto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sun.misc.BASE64Encoder;

public class ProductPhotoJDBC implements ProductPhoto_interface{
	
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=bakeryDB";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa123456";

	private static final String SELECT_PHOTO = "SELECT product_id , Product_photo , photo_id From Pro_photo where Product_id=? ORDER BY Photo_id";
	@Override
	public List<ProductPhotoBean> selectphoto(int product_id) {
		List<ProductPhotoBean> beans = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			 conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
//			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SELECT_PHOTO);
			pstmt.setInt(1, product_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductPhotoBean bean = new ProductPhotoBean();
				bean.setPhoto(rs.getBytes("Product_photo"));
				bean.setPhoto_id(rs.getInt("photo_id"));
				bean.setProduct_id(rs.getInt("product_id"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return beans;
	}

	private static final String INSERT = "insert into Pro_photo (Product_photo,Product_id) values(?,?)";

	@Override
	public void insertPhoto(ProductPhotoBean bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
//			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			if (bean != null) {
				pstmt.setBytes(1, bean.getPhoto());
				pstmt.setInt(2, bean.getProduct_id());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	private static final String DELETE = "Delete  FROM Pro_photo WHERE Photo_id=?";
	@Override
	public void deletePhoto(int Photo_id) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
//			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, Photo_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		// ProPhotoJNDI dao=new ProPhotoJNDI();
		// System.out.println( dao.selectphoto(2));

	}
}
	

