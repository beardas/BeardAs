package com.RanReco.vo;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEMBER_RESULT")
@Getter @Setter
public class MemberResultVO {
   @Id
   private String idx;
   private int memberIdx;
   private String memberLocation;
   private String memberLocationFile;
   private String memberFood;
   private String memberFoodFile;
   private String look;
   private String memberFashionFile;
   @Transient @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
   SimpleDateFormat r_date = new SimpleDateFormat("yyyyMMdd");
   @Transient @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
   Date rDate = new Date();
   
   private String regDate = r_date.format(rDate);


}