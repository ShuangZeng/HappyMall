package com.example.HappyMall.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

//Created and Edited by Mohammed Heakal
@Entity
public class Product {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private User vendor;

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	private double price;

	private int quantity;

	private String status;

	private String imageUrl;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;

	@Transient
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

//	public ProductDto toDto() {
//		ProductDto dto = new ProductDto();
//		dto.setId(id);
//		dto.setName(name);
//		dto.setPrice(price);
//		dto.setQuantity(quantity);
//		vendor.setListAddress(new ArrayList<>());
//		vendor.setListCardDetail(new ArrayList<>());
//		vendor.setListProduct(new ArrayList<>());
//		vendor.setListOrders(new ArrayList<>());
//		dto.setVendor(vendor);
//		dto.setDescription(description);
//		dto.setStatus(status);
//		dto.setModifiedDate(modifiedDate);
//		dto.setCreateDate(createDate);
//		dto.setListResouce(listResouce);
//		return dto;
//	}

}
