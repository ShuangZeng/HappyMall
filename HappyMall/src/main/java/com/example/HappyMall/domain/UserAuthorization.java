package com.example.HappyMall.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class UserAuthorization {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
//	@Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "id", columnDefinition = "BINARY(16)")
//    private UUID id;
	
	@OneToOne
	private User user;
	
	@NotBlank
	private String userName;
	
	@NotBlank	
	private String password;
	
	@NotNull
	private char active_Ind;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
}
