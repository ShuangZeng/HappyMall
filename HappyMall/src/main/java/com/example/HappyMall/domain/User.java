package com.example.HappyMall.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
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
	
	@OneToMany(mappedBy="user")
	private List<Address> listAddress;
	
	@OneToMany(mappedBy="user")
	private List<Orders> listOrders;
	
	@OneToMany(mappedBy="vendor")
	private List<Product> listProduct;
	
	@OneToMany(mappedBy="user")
	private List<CardDetail> listCardDetail;
}
