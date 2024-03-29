package com.happymall.webservice.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Orders {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@NotBlank
	private String orderCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipping_address_id")
	private Address shippingAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billing_address_id")
	private Address billingAddress;

	private double subTotal = 0.0;

	private double tax;

	private double serviceFee;

	private double total;

	private String status;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;

	@Transient
	private List<OrderLine> listOrderLine;

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

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public double getSubTotal() {
		if (subTotal == 0) {
			for (OrderLine L : listOrderLine) {
				subTotal += L.getTotal();
			}
		}
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTax() {
		if (tax == 0) {
			tax = 0.07 * getSubTotal();
		}
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getTotal() {
		if (total == 0) {
			total = getTax() + getSubTotal();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<OrderLine> getListOrderLine() {
		return listOrderLine;
	}

	public void setListOrderLine(List<OrderLine> listOrderLine) {
		this.listOrderLine = listOrderLine;
	}

	public Orders() {
		// TODO Auto-generated constructor stub
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Orders(User user, @NotBlank String orderCode, Address shippingAddress, Address billingAddress,
			String status) {
		super();
		this.user = user;
		this.orderCode = orderCode;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.status = status;
		this.createDate = new Date();
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", user=" + user + ", orderCode=" + orderCode + ", shippingAddress="
				+ shippingAddress + ", billingAddress=" + billingAddress + ", subTotal=" + subTotal + ", tax=" + tax
				+ ", serviceFee=" + serviceFee + ", total=" + total + ", status=" + status + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
