package com.orderlist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderListDAOJNDI {
	private DataSource ds;

	public OrderListDAOJNDI() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private final static String INSERT_ORDER_ITEMS = "insert into Ord_list(Order_id , Product_id) values(? , ?)";

	public void insertOrderItems(int orderId, List<OrderListBean> beans) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			for (OrderListBean bean : beans) {
				pstmt = conn.prepareStatement(INSERT_ORDER_ITEMS);
				pstmt.setInt(1, orderId);
				pstmt.setInt(2, bean.getProductId());
				pstmt.executeUpdate();
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
		}
	}
}
