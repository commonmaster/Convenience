package com.stone.springmvc.manage;

import org.springframework.web.multipart.MultipartFile;

public class 제품 {

	private int barcode;
	private String name;
	private String type;
	private int price;
	private String provider;
	private byte[] productImg;
	private MultipartFile productImgFile;
	private String intro;
	private int isExcluded;
	
	

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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
	
	public byte[] getProductImg() {
		try {
			if (productImgFile != null && productImg == null) {
				// 업로드된 파일을 byte형태로 돌려준다
				productImg = productImgFile.getBytes();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return productImg;
	}

	public void setProductImg(byte[] productImg) {
		this.productImg = productImg;
	}

		
	public MultipartFile getProductImgFile() {
		return productImgFile;
	}

	public void setProductImgFile(MultipartFile productImgFile) {
		this.productImgFile = productImgFile;
	}

	public int getIsExcluded() {
		return isExcluded;
	}

	public void setIsExcluded(int isExcluded) {
		this.isExcluded = isExcluded;
	}
	
	public String printNameUpperCase() {
		
		return name.toUpperCase();
	}
	
	
	
}
