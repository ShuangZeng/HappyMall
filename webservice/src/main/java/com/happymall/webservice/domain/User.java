package com.happymall.webservice.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.happymall.webservice.domain.Address;
import com.happymall.webservice.domain.CardDetail;
import com.happymall.webservice.domain.Orders;
import com.happymall.webservice.domain.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@OneToOne
	private Role role;
	
	@NotBlank
	private String fullName;
	
	@Email
	private String email;
	
	private String phone;
	
	@NotBlank
	private char active_Ind;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
	
	@JsonBackReference(value = "ownerOfAddress")
	@OneToMany(mappedBy="user")
	private List<Address> listAddress;
	
	@JsonBackReference(value = "ownerOfOrder")
	@OneToMany(mappedBy="user", cascade = CascadeType.PERSIST)
	private List<Orders> listOrders;
	
	@JsonBackReference(value = "ownerOfProduct")
	@OneToMany(mappedBy="vendor", cascade = CascadeType.PERSIST)
	private List<Product> listProduct;
	
	@JsonBackReference(value = "ownerOfCard")
	@OneToMany(mappedBy="user", cascade = CascadeType.PERSIST)
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
	
}
