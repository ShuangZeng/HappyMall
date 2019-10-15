package com.happymall.webservice.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class MockServer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(length = 16)
	private String cardNumber;
	
	@Column(length = 50)
	private String nameOnCard;
	
	@Column
	private double value;
	
	@Column
	private double remainingValue;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date issuedDate;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date expiredDate;
	
	@Column(length = 16)
	private String type;
	
	@Column(length = 1)
	private String activeInd;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getRemainingValue() {
		return remainingValue;
	}

	public void setRemainingValue(double remainingvalue) {
		this.remainingValue = remainingvalue;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(String active_Ind) {
		this.activeInd = active_Ind;
	}
	
	

}
