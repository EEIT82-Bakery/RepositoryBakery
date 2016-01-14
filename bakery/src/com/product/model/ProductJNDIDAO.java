package com.product.model;

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

import com.article.model.ArticleBean;

public class ProductJNDIDAO implements ProductDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO Product VALUES (? , ? , ? , ? , ? , ?, ?)";

	private static final String SELECT_ALL = "select Product_id, Product_name,Product_status,Product_price,Main_photo,Discount,Product_date,Product_type from Product p join product_type t on p.product_type_id=t.product_type_id";
	private static final String UPDATE = "update Product set Product_name=?, Product_status=?,Product_price=?,Main_photo=?,Discount=?,Product_date=?,product_type_id=? where Product_id=?";
	private static final String DELETE_PHOTO = "delete from Product_photo where Product_id=?";
	private static final String DELETE = "delete from Product where  Product_id=?";
	private static final String SELECT_BY_PHOTO = "select Main_photo from Product where Product_id=?";
	private static final String SELECT_BY_CAKE = "select *from product where product_type_id like ?";
	private static final String SELECT_TYPE="select * from product_type";
	
	
	
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
	public List<ProductBean> selectType(){
		List<ProductBean> result = null;
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_TYPE);
			rset = stmt.executeQuery();
			result = new ArrayList<ProductBean>();
			while (rset.next()) {
				ProductBean bean = new ProductBean();
				bean.setProductTypeId(rset.getInt("product_type_id"));
				bean.setProductType(rset.getString("Product_type"));
				result.add(bean);
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

		return result;
	}
	
	public List<ProductBean> getSelectType() {
		return selectType();
	}
	
	
	@Override
	public ProductBean selectPhoto(int productId){
		ProductBean result = null;
		ResultSet rset = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_PHOTO);
			stmt.setInt(1, productId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new ProductBean();
				result.setMain_photo(rset.getBytes("Main_photo"));
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

		return result;
	}

	private static final String SELECT_BY_ID = "select Product_id, Product_name,Product_status,Product_price,Main_photo,Discount,Product_date,Product_type from Product p join product_type t on p.product_type_id=t.product_type_id where Product_name like ? ";

	@Override
	public List<ProductBean> select(String productName) {
		List<ProductBean> beans = new ArrayList<>();
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setString(1, "%" + productName + "%");
			rset = stmt.executeQuery();
			while (rset.next()) {
				ProductBean bean = new ProductBean();
				bean.setProductId(rset.getInt("Product_id"));
				bean.setProductName(rset.getString("Product_name"));
				bean.setProductStatus(rset.getString("Product_status"));
				bean.setProductPrice(rset.getInt("Product_price"));
				bean.setMain_photo(rset.getBytes("Main_photo"));
				bean.setDiscount(rset.getDouble("Discount"));
				bean.setProductDate(rset.getDate("Product_date"));
				bean.setProductType(rset.getString("Product_type"));
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
	public List<ProductBean> selectAll() {
		List<ProductBean> result = null;
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			result = new ArrayList<ProductBean>();
			while (rset.next()) {
				ProductBean bean = new ProductBean();
				bean.setProductId(rset.getInt("Product_id"));
				bean.setProductName(rset.getString("Product_name"));
				bean.setProductType(rset.getString("Product_type"));
				bean.setProductStatus(rset.getString("Product_status"));
				bean.setProductPrice(rset.getInt("Product_price"));
				bean.setMain_photo(rset.getBytes("Main_photo"));
				bean.setDiscount(rset.getDouble("Discount"));
				bean.setProductDate(rset.getDate("Product_date"));
				result.add(bean);
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
		return result;
	}

	public List<ProductBean> getAllProducts() {
		return selectAll();
	}

	@Override
	public List<ProductBean> selectCake(String productType) {
		List<ProductBean> result = new ArrayList<ProductBean>();
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_CAKE);
			stmt.setString(1, productType);
			rset = stmt.executeQuery();
			while (rset.next()) {
				ProductBean bean = new ProductBean();
				bean.setProductId(rset.getInt("Product_id"));
				bean.setProductName(rset.getString("Product_name"));
				bean.setProductStatus(rset.getString("Product_status"));
				bean.setProductPrice(rset.getInt("Product_price"));
				bean.setMain_photo(rset.getBytes("Main_photo"));
				bean.setDiscount(rset.getDouble("Discount"));
				result.add(bean);
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
		return result;
	}

	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(INSERT_STMT);
			if (bean != null) {
				stmt.setString(1, bean.getProductName());
				stmt.setString(2, bean.getProductStatus());
				stmt.setInt(3, bean.getProductPrice());
				stmt.setBytes(4, bean.getMain_photo());
				stmt.setDouble(5, bean.getDiscount());
				java.util.Date productDate = bean.getProductDate();
				if (productDate != null) {
					long time = productDate.getTime();
					stmt.setDate(6, new java.sql.Date(time));
				} else {
					stmt.setDate(6, null);
				}
				stmt.setInt(7, bean.getProductTypeId());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = bean;
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
		return result;
	}

	@Override
	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, bean.getProductName());
			stmt.setString(2, bean.getProductStatus());
			stmt.setFloat(3, bean.getProductPrice());
			stmt.setBytes(4, bean.getMain_photo());
			stmt.setDouble(5, bean.getDiscount());
			java.util.Date productDate = bean.getProductDate();
			if (productDate != null) {
				long time = productDate.getTime();
				stmt.setDate(6, new java.sql.Date(time));
			} else {
				stmt.setDate(6, null);
			}
			stmt.setInt(7, bean.getProductTypeId());
			stmt.setInt(8, bean.getProductId());
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
		return result;
	}

	//計算資料筆數
	private static final String productCount="SELECT count(product_id) FROM product where  product_type_id=?";
	@Override
	public int getProductCount(int product_type_id){
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int result=0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(productCount);
			pstmt.setInt(1, product_type_id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return result;
	}
	
//	//計算資料數量
//	private static final String  Product_page="Select * From(SELECT ROW_NUMBER() OVER (ORDER BY Product_id ) AS RowNum,"
//		    + "* FROM Product where product_type_id=?)as temp where RowNum >= ? AND RowNum <= ?";
//	
//	@Override
//	public List<ProductBean> getListByRow(int product_type_id, int startRow,int endRow){
//		PreparedStatement pstmt = null;
//		Connection conn = null;
//		ResultSet rs = null;
//		List<ProductBean> beans = new ArrayList<ProductBean>();
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(Product_page);
//			pstmt.setInt(1, product_type_id);
//			pstmt.setInt(2, startRow);
//			pstmt.setInt(3, endRow);
//			rs=pstmt.executeQuery();
//			while(rs.next()){
//				ProductBean bean = new ProductBean();
//				bean.setProductId(rs.getInt("Product_id"));
//				bean.setProductName(rs.getString("Product_name"));
//				bean.setProductType(rs.getString("Product_type_id"));
//				bean.setProductStatus(rs.getString("Product_status"));
//				bean.setProductPrice(rs.getInt("Product_price"));
//				bean.setMain_photo(rs.getBytes("Main_photo"));
//				bean.setDiscount(rs.getDouble("Discount"));
//				bean.setProductDate(rs.getDate("Product_date"));
//				beans.add(bean);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return beans; 
//	}
	
	@Override
	public void delete(int productId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(DELETE_PHOTO);
			stmt.setInt(1, productId);
			stmt.executeUpdate();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, productId);
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
	@Override
	public List<ProductBean> selectByPage(int pageInt ,int product_type_id) 
	{
		List<ProductBean> result = null;
		ResultSet rset = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("select * FROM Product where product_type_id = "+product_type_id+" ORDER BY Product_id OFFSET 6 * (" + (pageInt - 1) + ") ROWS FETCH NEXT 6 ROWS ONLY");
			rset = stmt.executeQuery();
			result = new ArrayList<ProductBean>();
			while (rset.next()) {
				ProductBean bean = new ProductBean();
				bean.setProductId(rset.getInt("Product_id"));
				bean.setProductName(rset.getString("Product_name"));
				bean.setProductTypeId(rset.getInt("Product_type_id"));
				bean.setProductStatus(rset.getString("Product_status"));
				bean.setProductPrice(rset.getInt("Product_price"));
				bean.setMain_photo(rset.getBytes("Main_photo"));
				bean.setDiscount(rset.getDouble("Discount"));
				bean.setProductDate(rset.getDate("Product_date"));
				result.add(bean);
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
	return result;
	}

}