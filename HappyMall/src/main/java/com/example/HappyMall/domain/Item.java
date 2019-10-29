package com.example.HappyMall.domain;

public class Item {

	private Product product;

	private int quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
}
