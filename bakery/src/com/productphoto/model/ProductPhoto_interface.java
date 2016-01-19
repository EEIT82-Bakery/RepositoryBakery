package com.productphoto.model;

import java.util.List;

public interface ProductPhoto_interface {

	public abstract List<ProductPhotoBean> selectphoto(int product_id);

	public abstract void insertPhoto(ProductPhotoBean bean);

	public abstract void deletePhoto(int Photo_id);
	

}