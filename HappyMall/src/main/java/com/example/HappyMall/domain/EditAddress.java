package com.example.HappyMall.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditAddress {

	@NotBlank
	@Size(max = 100)
	private String name;

	@NotBlank
	@Pattern(regexp = "^(\\d{3})-\\d{3}-\\d{4}$")
	private String phoneNumber;

	@NotBlank
	@Size(max = 100)
	private String lineOne;

	@NotBlank
	@Size(max = 100)
	private String lineTwo;

	public EditAddress() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	@NotBlank
	@Size(max = 100)
	private String city;

	@NotBlank
	@Size(max = 100)
	private String state;

	@NotBlank
	@Pattern(regexp = "^\\d{5}$")
	private String zipcode;
}
