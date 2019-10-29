package com.example.HappyMall.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SystemConfig {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int serviceFee;

	private int tax;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Future
	private Date appliedDate;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;

	public SystemConfig() {
		// TODO Auto-generated constructor stub
		this.createDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(int serviceFee) {
		this.serviceFee = serviceFee;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public String getAppliedDateString() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		return formatter.format(appliedDate);
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getCreateDateString() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		return formatter.format(createDate);
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

	public boolean checkEdit() {
		Date now = new Date();
		return this.appliedDate.after(now) ? true : false;
	}

	public SystemConfig(int tax, int serviceFee, Date appliedDate) {
		// TODO Auto-generated constructor stub
		this.tax = tax;
		this.serviceFee = serviceFee;
		this.createDate = new Date();
		this.appliedDate = appliedDate;
	}

	@Override
	public String toString() {
		return "SystemConfig [id=" + id + ", serviceFee=" + serviceFee + ", tax=" + tax + ", appliedDate=" + appliedDate
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
