package com.happymall.webservice.dto;

import java.util.Date;
import java.util.List;

import com.happymall.webservice.domain.Address;
import com.happymall.webservice.domain.CardDetail;
import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.domain.Product;
import com.happymall.webservice.domain.Role;

public class UserDto {

	private int id;

	private Role role;

	private String fullName;

	private String userName;

	private String email;

	private String password;

	private String phone;

	private char active_Ind;

	private boolean enable = true;

	private Date createDate;

	private Date modifiedDate;

	private List<Address> listAddress;

	private List<Orders> listOrders;

	private List<Product> listProduct;

	private List<CardDetail> listCardDetail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public char getActive_Ind() {
		return active_Ind;
	}

	public void setActive_Ind(char active_Ind) {
		this.active_Ind = active_Ind;
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

	public List<Address> getListAddress() {
		return listAddress;
	}

	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}

	public List<Orders> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<Orders> listOrders) {
		this.listOrders = listOrders;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public List<CardDetail> getListCardDetail() {
		return listCardDetail;
	}

	public void setListCardDetail(List<CardDetail> listCardDetail) {
		this.listCardDetail = listCardDetail;
	}

	public String toString() {
		return getFullName();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setZipcode(String zipcode) {
		for (Address address : listAddress) {
			address.setZipcode(zipcode);
		}
	}

	public void setlineOne(String lineOne) {
		for (Address address : listAddress) {
			address.setLineOne(lineOne);
		}
	}

	public void setlineTwo(String lineTwo) {
		for (Address address : listAddress) {
			address.setLineTwo(lineTwo);
		}
	}

	public void setcity(String city) {
		for (Address address : listAddress) {
			address.setCity(city);
		}
	}

	public void setstate(String state) {
		for (Address address : listAddress) {
			address.setState(state);
		}
	}

}
