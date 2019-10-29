package com.example.HappyMall.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonSerialize
public class UserAuthorization {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	private User user;

	@NotBlank
	private String userName;

	@NotBlank
	private String password;

	@NotBlank
	private char active_Ind;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createDate;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date modifiedDate;
}
