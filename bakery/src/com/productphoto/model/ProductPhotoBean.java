package com.productphoto.model;

import java.util.Arrays;

public class ProductPhotoBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int photo_id;
	private byte[] photo;
	private String photo1;
	private int product_id;

	public String getPhoto1() {
		return photo1;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	@Override
	public String toString() {
		return photo_id + Arrays.toString(photo) + +product_id;
	}

	public int getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
}
