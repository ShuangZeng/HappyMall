package com.example.HappyMall.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CardDetail {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
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
		
}
