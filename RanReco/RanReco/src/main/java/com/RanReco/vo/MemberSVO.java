package com.RanReco.vo;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSVO extends Paging{
	
	 private int idx;
	 private String id;
	 private String password;
	 private String name;
	 private String email;
	 private String phoneNumber;
	 private String admin;
	 private String address;
	 private String address2;
	 private String fileName; 
	 private String regDate;
	 
	 private String[] idxs;

	
}