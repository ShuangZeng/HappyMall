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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Payment {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@ManyToOne
	@JoinColumn(name="card_detail_id")
	private CardDetail cardDetail;
	
	@OneToOne
	private Orders orders;
	
	private double paymentTotal;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date paymentDate;
	
	private String Status;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
	
	@OneToMany(mappedBy="payment")
	private List<Transaction> listTransaction;
}
