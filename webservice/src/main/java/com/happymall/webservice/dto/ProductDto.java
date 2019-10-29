package com.happymall.webservice.dto;

import java.util.Date;
import java.util.List;

import com.happymall.webservice.domain.Product;
import com.happymall.webservice.domain.Resource;
import com.happymall.webservice.domain.User;

public class ProductDto {

	private int id;

	private User vendor;

	private String name;

	private String description;

	private double price;

	private int quantity;

	private String status;

	private Date createDate;

	private Date modifiedDate;

	private List<Resource> listResouce;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getVendor() {
		return vendor;
	}

	public void setVendor(User vendor) {
		this.vendor = vendor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<Resource> getListResouce() {
		return listResouce;
	}

	public void setListResouce(List<Resource> listResouce) {
		this.listResouce = listResouce;
	}

	public Product toProduct() {
		Product dto = new Product();
		dto.setId(id);
		dto.setName(name);
		dto.setPrice(price);
		dto.setQuantity(quantity);
//		vendor.setListAddress(li);
//		vendor.setListCardDetail(null);
//		vendor.setListProduct(null);
//		vendor.setListOrders(null);
		dto.setVendor(vendor);
		dto.setDescription(description);
		dto.setStatus(status);
		dto.setModifiedDate(modifiedDate);
		dto.setCreateDate(createDate);
		dto.setListResouce(listResouce);
		return dto;
	}

}
