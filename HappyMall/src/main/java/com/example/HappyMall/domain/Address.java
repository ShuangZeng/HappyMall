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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Address {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String lineOne;
	
	private String lineTwo;

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	private String zipcode;
	
	private boolean default_addr;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
	
	@JsonIgnoreProperties("listOrdersShipping")
	@OneToMany(mappedBy="shippingAddress")
	private List<Orders> listOrdersShipping;
	
	@JsonIgnoreProperties("listOrdersBilling")
	@OneToMany(mappedBy="billingAddress")
	private List<Orders> listOrdersBilling;


	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(lineOne);
		if (lineTwo != null && lineTwo != "")
			result.append(lineTwo + ", ");
		result.append(city + ", " + state + ", " + zipcode);
		return result.toString();
	}
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getLineOne() {
		return lineOne;
	}

	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}

	public String getLineTwo() {
		return lineTwo;
	}

	public void setLineTwo(String lineTwo) {
		this.lineTwo = lineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isDefault_addr() {
		return default_addr;
	}

	public void setDefault_addr(boolean default_addr) {
		this.default_addr = default_addr;
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

	public List<Orders> getListOrdersShipping() {
		return listOrdersShipping;
	}

	public void setListOrdersShipping(List<Orders> listOrdersShipping) {
		this.listOrdersShipping = listOrdersShipping;
	}

	public List<Orders> getListOrdersBilling() {
		return listOrdersBilling;
	}

	public void setListOrdersBilling(List<Orders> listOrdersBilling) {
		this.listOrdersBilling = listOrdersBilling;
	}

	
	
}
