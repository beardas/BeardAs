package com.RanReco.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@SequenceGenerator(
        name="MEMBER_SEQ_GEN",
        sequenceName="MEMBER_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name = "MEMBER")
@Getter @Setter
public class MemberVO {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="MEMBER_SEQ_GEN"        
            )
	private int idx;
	@Column(name = "ID", unique = true)
	private String id;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "NAME")
	private String name;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	@Column(name = "ADMIN")
	private String admin;
	@Column(name = "PROVIDER")
	private String provider;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "ADDRESS2")
	private String address2;
	@Column(name = "REG_DATE")
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();
	
	
	@Column(name = "FILE_NAME")
	private String fileName; 
	

	@Transient
	@Column(name = "LOCATION")
	private String location;

	@Transient
	@Column(name = "FOOD")
	private String food;
	
	@Transient
	@Column(name = "FOOD_FILE")
	private String foodFile;
	
	@Transient
	@Column(name = "LOOK")
	private String look;

	@Transient
	@Column(name = "LOOK_FILE")
	private String lookFile;
	}
