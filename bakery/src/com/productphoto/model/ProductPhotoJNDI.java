package com.productphoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductPhotoJNDI implements ProductPhoto_interface {
	private static DataSource dataSource = null;

	static {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_PHOTO = "SELECT product_id , Product_photo , photo_id From product_photo where Product_id=? ORDER BY Photo_id";
	@Override
	public List<ProductPhotoBean> selectphoto(int product_id) {
		List<ProductPhotoBean> beans = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			// conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			conn = dataSource.getConnection();
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

	private static final String INSERT = "insert into product_photo (Product_photo,Product_id) values(?,?)";

	@Override
	public void insertPhoto(ProductPhotoBean bean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
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

	private static final String DELETE = "Delete  FROM product_photo WHERE Photo_id=?";
	@Override
	public void deletePhoto(int Photo_id) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
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
