package com.orderlist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order.model.OrderBean;
import com.product.model.ProductBean;


public class OrderListJNDIDAO implements OrderList_interface{
	
	private static DataSource dataSource = null;

	static {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	 
	private static final String SELECTPRODUCTLIST = "select Product_id,Quantity from Ord_list where Order_id=?";
			  
	@Override
	public List<OrderListBean> selectProductList(int OrderId) {
		List<OrderListBean> beans = new ArrayList<>();
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECTPRODUCTLIST);
			stmt.setInt(1, OrderId);
			rset = stmt.executeQuery();
			while (rset.next()) {
				OrderListBean bean = new OrderListBean();
				bean.setQuantity(rset.getInt("Quantity"));
				bean.setProductId(rset.getInt("Product_id"));
				beans.add(bean);
			}
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

		return beans;
	}
	
	private static final String INSERT_ORDER_ITEMS = "insert into Ord_list(Order_id , Product_id, Quantity) values(? , ?, ?)";
	@Override
	public void insertOrderItems(int orderId, Vector<OrderListBean> beans) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			for (OrderListBean bean : beans) {
				stmt = conn.prepareStatement(INSERT_ORDER_ITEMS);
				stmt.setInt(1, orderId);
				stmt.setInt(2, bean.getProductId());
				stmt.setInt(3, bean.getQuantity());
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
		Connection conn=null;
		try {
			conn = dataSource.getConnection();
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
	
	private static final String SELECT_TOP3="select top(3) product_id , sum(Quantity) as 'Quantity' from Ord_list group by product_id order by Quantity desc";
	
	public List<OrderListBean> selectTop3(){
		List<OrderListBean> beans = new ArrayList<OrderListBean>();
		ResultSet rset = null;
		Connection conn = null;
		OrderListBean bean =null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_TOP3);
			rset = stmt.executeQuery();
			while (rset.next()) {
				bean= new OrderListBean();
				bean.setQuantity(rset.getInt("Quantity"));
				bean.setProductId(rset.getInt("Product_id"));
				beans.add(bean);
			}
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
		return beans;
	}
	
	
	
	
	public static void main(String args[]){
	
//		OrderListJNDIDAO dao =new OrderListJNDIDAO();
//		System.out.println(dao.selectTop3());
//		List<OrderListBean> beans=new ArrayList<>();
//		OrderListBean bean =new OrderListBean();
//		bean.setProductId(5);
//		bean.setCount(5);	
//		beans.add(bean);
//		dao.insertOrderItems(3, beans);
	}
}