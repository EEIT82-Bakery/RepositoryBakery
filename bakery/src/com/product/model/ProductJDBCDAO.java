package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBCDAO implements ProductDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO Product VALUES (? , ? , ? , ? , ? , ?, ?)";
	private static final String SELECT_BY_ID = "select * from Product where Product_name=?";
	private static final String SELECT_ALL = "select * from Product";
	private static final String UPDATE = "update Product set Product_name=?, Product_status=?,Product_price?,Product_photo=?,Discount?,Product_date=?,Protype_id=? where Product_id=?";
	private static final String DELETE_PHOTO = "delete from Pro_photo where Product_id=?";
	private static final String DELETE = "delete from Product where  Product_id=?";

	private static final String SELECT_BY_PHOTO = "select product_photo from Product where Product_id=?";
	private static final String SELECT_BY_CAKE = "select *from product where protype_id like ?";

	@Override
	public List<ProductBean> select(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBean> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductBean insert(ProductBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductBean update(ProductBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductBean> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductBean selectPhoto(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBean> selectCake(String productType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBean> selectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBean> getSelectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProductCount(int product_type_id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<ProductBean> selectByPage(int pageInt, int product_type_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductBean selectId(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
