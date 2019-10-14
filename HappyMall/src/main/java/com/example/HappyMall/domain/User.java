package com.example.HappyMall.domain;

import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "user")
public class User {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int Id;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Role role;
	
	@NotBlank(message="FullName field is mandatory")
	private String fullName;
	
	@NotBlank
	private String userName;

	
	@Email(message="Email is not valid")
	private String email;
	
	@NotBlank	
	private String password;
	
	@Pattern(regexp="(^$[0-9]{10})",message="Mobile number must be 10 digits")
	private String phone;
	
	@NotNull
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
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public char getActive_Ind() {
		return active_Ind;
	}

	public void setActive_Ind(char active_Ind) {
		this.active_Ind = active_Ind;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
