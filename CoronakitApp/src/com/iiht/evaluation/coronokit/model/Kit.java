package com.iiht.evaluation.coronokit.model;

import java.util.List;

public class Kit {

	private String kitId;
	private List<ProductMaster> products;
	private int productCount;
	private int totalAmount;

	public String getKitId() {
		return kitId;
	}

	public void setKitId(String kitId) {
		this.kitId = kitId;
	}

	public List<ProductMaster> getProducts() {
		return products;
	}

	public void setProducts(List<ProductMaster> products) {
		this.products = products;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	

}
