package com.productphoto.model;

import java.util.List;

import com.product.model.ProductBean;
import com.product.model.ProductJNDIDAO;

public class ProductPhotoService {
	private ProductPhoto_interface dao;

	public ProductPhotoService() {
		dao = new ProductPhotoJNDI();
	}

	public List<ProductPhotoBean> select(int productId) {
		List<ProductPhotoBean> beans = dao.selectphoto(productId);
		return beans;
	}
	
	public void delect(int Photo_id){
		dao.deletePhoto(Photo_id);
	}

}
