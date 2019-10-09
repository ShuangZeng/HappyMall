package com.example.HappyMall.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Address {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@ManyToOne
	@JoinColumn(name="user_Id")
	private User user;
	
	@NotBlank
	private String strLine1;
	
	private String strLine2;

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
}
