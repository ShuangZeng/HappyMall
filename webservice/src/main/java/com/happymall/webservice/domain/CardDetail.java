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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CardDetail {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@NotBlank
	private String cardNumber;
	
	@NotBlank
	private String nameOnCard;

	private double value;
	
	private double remainingValue;
	
	@Past
	private Date issuedDate;

	@Future
	private Date expiredDate;
	
	@OneToOne
	private Address address;
	
	private String type;
	
	@NotBlank
	private char active_Ind;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
	
	@OneToMany(mappedBy="cardDetail")
	private List<Payment> listPayment;

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

	public void setRemainingValue(double remainingValue) {
		this.remainingValue = remainingValue;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<Payment> getListPayment() {
		return listPayment;
	}

	public void setListPayment(List<Payment> listPayment) {
		this.listPayment = listPayment;
	}
	
	
		
}
