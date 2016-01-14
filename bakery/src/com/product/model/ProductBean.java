package com.product.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;



public class ProductBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private int productTypeId;
	private String productType;
	private int productId;
	private String productName;
	private String productStatus;
	private int productPrice;
	private byte[] main_photo;
	private double discount;
	private java.util.Date productDate;
	private int photoId;
	private byte[] productPhoto;
	private int quantity;

	public ProductBean(){
		
	}
	
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public byte[] getMain_photo() {
		return main_photo;
	}
	public void setMain_photo(byte[] main_photo) {
		this.main_photo = main_photo;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public java.util.Date getProductDate() {
		return productDate;
	}
	public void setProductDate(java.util.Date productDate) {
		this.productDate = productDate;
	}
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	public byte[] getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductBean [productTypeId=" + productTypeId + ", productType=" + productType + ", productId="
				+ productId + ", productName=" + productName + ", productStatus=" + productStatus + ", productPrice="
				+ productPrice + ", main_photo=" + Arrays.toString(main_photo) + ", discount=" + discount
				+ ", productDate=" + productDate + ", photoId=" + photoId + ", productPhoto="
				+ Arrays.toString(productPhoto) + ", quantity=" + quantity + "]";
	}

	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static java.util.Date convertDate(String data) {
		java.util.Date result = null;
		try {
			result = sFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			result = new java.util.Date(0);
		}
		return result;
	}

}

	
	