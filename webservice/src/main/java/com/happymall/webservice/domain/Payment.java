package com.happymall.webservice.domain;

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
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Payment {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@ManyToOne
	@JoinColumn(name="card_detail_id")
	private CardDetail cardDetail;
	
	@OneToOne
	private Orders orders;
	
	private double paymentTotal;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date paymentDate;
	
	private String Status;

	public int getId() {
		return id;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CardDetail getCardDetail() {
		return cardDetail;
	}

	public void setCardDetail(CardDetail cardDetail) {
		this.cardDetail = cardDetail;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public double getPaymentTotal() {
		return paymentTotal;
	}

	public void setPaymentTotal(double paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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

	public List<Transaction> getListTransaction() {
		return listTransaction;
	}

	public void setListTransaction(List<Transaction> listTransaction) {
		this.listTransaction = listTransaction;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
	
	@OneToMany(mappedBy="payment")
	private List<Transaction> listTransaction;
}
