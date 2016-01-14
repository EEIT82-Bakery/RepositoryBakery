package com.orderlist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class OrderListJDBCDAO implements OrderList_interface{
	
	
Connection conn=null;
	
	public void getConnection() {
		try {
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=bakeryDB";
			conn = DriverManager.getConnection(connUrl, "sa", "sa123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final String INSERT_ORDER_ITEMS = "insert into Ord_list(Order_id , Product_id,Count) values(? , ?, ?)";

	public void insertOrderItems(int orderId, List<OrderListBean> beans) {
		PreparedStatement stmt = null;
		try {
			for (OrderListBean bean : beans) {
				stmt = conn.prepareStatement(INSERT_ORDER_ITEMS);
				stmt.setInt(1, orderId);
				stmt.setInt(2, bean.getProductId());
				stmt.setInt(3, bean.getCount());
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
	
	private static final String DELETE="delete from Ord_list where Order_id=?";
	@Override
	public void delete(int orderId) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, orderId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String args[]){
		OrderListJNDIDAO dao =new OrderListJNDIDAO();
//		dao.getConnection();
		List<OrderListBean> beans=new ArrayList<>();
		OrderListBean bean =new OrderListBean();
		bean.setProductId(5);
		bean.setCount(5);	
		beans.add(bean);
//		dao.insertOrderItems(3, beans);
	}


	@Override
	public void insertOrderItems(int orderId, Vector<OrderListBean> beans) {
		// TODO Auto-generated method stub
		
	}
}
