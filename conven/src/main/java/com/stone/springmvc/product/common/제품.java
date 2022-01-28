package com.stone.springmvc.product.common;

public class 제품 {

	private int barcode;
	private String name;
	private String type;
	private int price;
	private String provider;
	private String imgUrl;
	private String intro;
	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() {	
		return imgUrl;
	}
	
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String providier) {
		this.provider = providier;
	}
	
	public String printNameUpperCase() {
		
		return name.toUpperCase();
	}
	
	
	
}
