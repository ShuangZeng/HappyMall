package com.example.HappyMall.domain;

public class Item {

	private Product product;
	
	private String name;
	private double price;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description;
	
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
	
	public Item (Product product, int quantity)
	{
		this.product = product;
		this.quantity = quantity;
	}
	
	public Item (String name, double price, String description, int quantity)
	{
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}
}
