package com.product.model;

import java.util.ArrayList;
import java.util.List;


public class ProductService {
	private ProductDAO_interface productDao = new ProductJNDIDAO();
	
	//計算總筆數
	public int getProductCount(int productTypeId){	
		return productDao.getProductCount(productTypeId);
	}
	
	
	
//	int count=300;
//	int pageIndex=5;
//	int pageSize=6;
//	/**
//	 * 取得分頁的所有資料
//	 * @param product_type_id 取得類型
//	 * @param pageSize 設定一頁有幾筆
//	 * @param pageIndex 目前第幾頁
//	 * @return
//	 */
//	public List<ProductBean> getListByPage(int product_type_id,int pageSize,int pageIndex){
//		int startRow = (pageIndex - 1) * pageSize + 1;
//		int endRow = (pageIndex) * pageSize;
//		return productDao.getListByRow(product_type_id, startRow, endRow);
//	}
	
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null) {
			result = productDao.insert(bean);
		}
		return result;
	}

	public List<ProductBean> select(String productName){
		List<ProductBean> result = null;
		if(productName!=null) {
			result = productDao.select(productName);
		}
		return result;
		
	}

	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if(bean!=null) {
			result = productDao.update(bean);
		}
		return result;
	}
	
	public void delete(int productId) {
		if(productId!=0) {
			 productDao.delete(productId);
		}
	}
	
	public List<ProductBean> selectCake(String productType){
		ProductJNDIDAO dao=new ProductJNDIDAO();	
		return dao.selectCake(productType);
	}
	
	public List<ProductBean> getAllProducts() {
		return productDao.getAllProducts();
	}
	public List<ProductBean> getSelectType() {
		return productDao.getSelectType();
	}

	public List<ProductBean> selectPaging(int pageInt,int product_type_id) {
		
		return productDao.selectByPage(pageInt,product_type_id);
	}
}
