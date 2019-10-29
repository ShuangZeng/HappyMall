package com.happymall.webservice.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Address {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

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

	@Transient
	private List<Orders> listOrdersShipping;

	@Transient
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
