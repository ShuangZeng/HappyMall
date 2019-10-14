package com.example.HappyMall.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product {
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
	
	@ManyToOne
	@JoinColumn(name="vendor_id")
	private User vendor;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	private double price;
	
	private int quantity;
	
	private String status;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
	
	@OneToMany(mappedBy="product")
	private List<Resource> listResouce;

	public Product() {
		super();
	}
	

	public Product(UUID id, User vendor, @NotBlank String name, @NotBlank String description, double price,
			int quantity, String status, Date createDate, Date modifiedDate, List<Resource> listResouce) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.listResouce = listResouce;
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

}
