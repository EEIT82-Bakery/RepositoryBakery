package com.product.model;

import java.util.ArrayList;
import java.util.List;

import com.orderlist.model.OrderListBean;

public class ProductService {
	private ProductDAO_interface productDao = new ProductJNDIDAO();

	// 計算總筆數
	public int getProductCount(int productTypeId) {
		return productDao.getProductCount(productTypeId);
	}

	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = productDao.insert(bean);
		}
		return result;
	}

	public ProductBean selectId(int productId) {
		ProductBean result = null;
		if (productId != 0) {
			result = productDao.selectId(productId);
		}
		return result;
	}

	public List<ProductBean> select(String productName) {
		List<ProductBean> result = null;
		if (productName != null) {
			result = productDao.select(productName);
		}
		return result;

	}

	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = productDao.update(bean);
		}
		return result;
	}

	public void delete(int productId) {
		if (productId != 0) {
			productDao.delete(productId);
		}
	}

	public List<ProductBean> selectCake(String productType) {
		ProductJNDIDAO dao = new ProductJNDIDAO();
		return dao.selectCake(productType);
	}

	public List<ProductBean> getAllProducts() {
		List<ProductBean> beans = productDao.getAllProducts();
		List<ProductBean> newBeans = new ArrayList<ProductBean>();
		for (ProductBean one : beans) {
			ProductBean bean1 = new ProductBean();
			String str = one.getProductStatus();
			if (str.length() >= 40L) {
				String sr = str.substring(0, 40);
				bean1.setProductStatus(sr + "...");
			} else {
				bean1.setProductStatus(str);
			}
			bean1.setProductId(one.getProductId());
			bean1.setProductName(one.getProductName());
			bean1.setProductType(one.getProductType());
			bean1.setProductPrice(one.getProductPrice());
			bean1.setMain_photo(one.getMain_photo());
			bean1.setDiscount(one.getDiscount());
			bean1.setProductDate(one.getProductDate());
			newBeans.add(bean1);
		}
		return newBeans;
	}
	
	public List<ProductBean> getSelectType() {
		return productDao.getSelectType();
	}

	public List<ProductBean> selectPaging(int pageInt, int product_type_id) {
		return productDao.selectByPage(pageInt, product_type_id);
	}
}
