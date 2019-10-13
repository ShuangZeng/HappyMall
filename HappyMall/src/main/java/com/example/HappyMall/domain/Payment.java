package com.example.HappyMall.domain;

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

//	@javax.persistence.Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int Id;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
	
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
