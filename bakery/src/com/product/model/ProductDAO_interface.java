package com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	public abstract List<ProductBean> select(String productName);
	public abstract List<ProductBean> selectAll();
	public abstract ProductBean insert(ProductBean bean);
	public abstract ProductBean update(ProductBean bean);
	public abstract void delete(int productId);
	public abstract List<ProductBean> getAllProducts();
	public abstract ProductBean selectPhoto(int productId);
	public abstract List<ProductBean> selectCake(String productTypeId);
	public abstract List<ProductBean> selectType();
	public abstract List<ProductBean> getSelectType();
	public abstract int getProductCount(int product_type_id);
	/**
	 * 單一類型產品資料數量
	 * @param product_type_id 產品類型
	 * @param startRow 開始筆數
	 * @param endRow 結束筆數
	 * @return
	 */
//	public abstract List<ProductBean> getListByRow(int product_type_id, int startRow,int endRow);
	public abstract List<ProductBean> selectByPage(int pageInt ,int product_type_id);
}
