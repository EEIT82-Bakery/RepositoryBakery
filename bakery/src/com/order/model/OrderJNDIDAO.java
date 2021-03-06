package com.order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductBean;




public class OrderJNDIDAO implements OrderDAO_interface{
	private static final String INSERT=" insert into Ord (Order_name,Order_phone,Order_address,Total_amount,Order_date,Ship_date,Cancel_date,Member_id,Order_status)values (?,?,?,?,GETDATE(),?,null,?,1)";
	private static final String GET_ORDER_ID = "select MAX(Order_id) as Order_id from Ord";
	private static final String SELECT_ID="select * from Ord where Order_id=?";
	private static final String SELECT_MEMBERID="select * from Ord where member_id=? order by Order_status , Order_id desc";
	private static final String UPDATE="update Ord set Order_status=? where Order_id=?";
	private static final String DELETE=" delete from Ord where Order_id=?";
	private static final String SELECTLIST=" select Order_id,Order_name,Ship_date,Member_id,Order_status from Ord order by Order_status , Order_id desc";
	
	private static DataSource dataSource = null;

	static {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/cake");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
			@Override
			public List<OrderBean> selectMember(int memberId) {
				List<OrderBean> beans = new ArrayList<>();
				ResultSet rset = null;
				Connection conn = null;
				PreparedStatement stmt = null;
				try {
					conn = dataSource.getConnection();
					stmt = conn.prepareStatement(SELECT_MEMBERID);
					stmt.setInt(1, memberId);
					rset = stmt.executeQuery();
					while (rset.next()) {
						OrderBean bean = new OrderBean();
						bean.setOrderId(rset.getInt("Order_id"));
						bean.setOrderName(rset.getString("Order_name"));
						bean.setOrderPhone(rset.getString("Order_phone"));
						bean.setOrderAddress(rset.getString("Order_Address"));
						bean.setTotalAmount(rset.getInt("Total_amount"));
						bean.setOrderDate(rset.getDate("Order_date"));
						bean.setCancelDate(rset.getDate("Cancel_date"));
						bean.setShipDate(rset.getDate("Ship_date"));
						bean.setMemberId(rset.getInt("Member_id"));
						bean.setOrderStaus(rset.getInt("Order_status"));				
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
			
	@Override
	public List<OrderBean> selectList() {
		List<OrderBean> beans = new ArrayList<>();
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECTLIST);
			rset = stmt.executeQuery();
			while (rset.next()) {
				OrderBean bean = new OrderBean();
				bean.setMemberId(rset.getInt("Member_id"));
				bean.setOrderId(rset.getInt("Order_id"));
				bean.setOrderName(rset.getString("Order_name"));
				bean.setShipDate(rset.getDate("Ship_date"));
				bean.setOrderStaus(rset.getInt("Order_status"));
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
		
	
	@Override
	public List<OrderBean> select(int OrderId) {
		List<OrderBean> beans = new ArrayList<>();
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_ID);
			stmt.setInt(1, OrderId);
			rset = stmt.executeQuery();
			while (rset.next()) {
				OrderBean bean = new OrderBean();
				bean.setOrderId(rset.getInt("Order_id"));
				bean.setOrderName(rset.getString("Order_name"));
				bean.setOrderPhone(rset.getString("Order_phone"));
				bean.setOrderAddress(rset.getString("Order_Address"));
				bean.setTotalAmount(rset.getInt("Total_amount"));
				bean.setOrderDate(rset.getDate("Order_date"));
				bean.setCancelDate(rset.getDate("Cancel_date"));
				bean.setShipDate(rset.getDate("Ship_date"));
				bean.setMemberId(rset.getInt("Member_id"));
				bean.setOrderStaus(rset.getInt("Order_status"));				
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
	
	@Override
	public int insertOrder(OrderBean bean) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Connection conn=null;
		int orderId = 0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT);
			if (bean != null) {
				stmt.setString(1, bean.getOrderName());
				stmt.setString(2, bean.getOrderPhone());
				stmt.setString(3, bean.getOrderAddress());
				stmt.setInt(4, bean.getTotalAmount());
				stmt.setDate(5,new java.sql.Date(bean.getShipDate().getTime()));
				stmt.setInt(6,bean.getMemberId());				
				int i = stmt.executeUpdate();
				if(i==1){
					stmt =conn.prepareStatement(GET_ORDER_ID);
					rset = stmt.executeQuery();
					if(rset.next()){
						orderId = rset.getInt("Order_id");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return orderId;
	}
	
	public void update(OrderBean bean){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn=dataSource.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, bean.getOrderStaus());	
			stmt.setInt(2, bean.getOrderId());
			int i = stmt.executeUpdate();

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
	
	@Override
	public void delete(int orderId) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn=dataSource.getConnection();
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
	
	
	
	public static void main (String args[]){
		OrderJNDIDAO dao =new OrderJNDIDAO();
//		OrderBean dao1 =new OrderBean();
//		dao.getConnection();
//		System.out.println(dao.select(5));
//		dao.delete(2);
	
//		dao.selectList(3);
		
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//		dao1.setOrderName("禮節臭腦包");
//		dao1.setOrderPhone("0912345778");
//		dao1.setOrderAddress("美國");
//		dao1.setTotalAmount(400);
//		dao1.setOrderDate(new java.sql.Date(new java.util.Date().getTime()));
//		dao1.setShipDate(java.sql.Date.valueOf("2015-05-16"));
//		dao1.setCancelDate(null);
//		dao1.setMemberId(3);
//		dao1.setOrderStaus(1);
//		dao.insertOrder(dao1);
	
	}


	
}
