package com.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDAOJNDI {
	
	private DataSource ds ;
	public OrderDAOJNDI() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private final static String INSERT_ORDER = "insert into Ord (order_name) values(?)";
	private final static String GET_ORDER_ID = "select MAX(Order_id) as Order_id from Ord";
	
	public int insertOrder(int orderName){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int orderId = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_ORDER);
			pstmt.setInt(1, orderName);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				pstmt = conn.prepareStatement(GET_ORDER_ID);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					orderId = rs.getInt("Order_id");
				}
			}
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orderId;
	}
	
}
