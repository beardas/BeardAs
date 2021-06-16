package com.RanReco.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@SequenceGenerator(
        name="FASHION_SEQ_GEN",
        sequenceName="FASHION_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="FASHION")
@Getter @Setter
public class FashionVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="FASHION_SEQ_GEN"        
            )
	@Column(name = "IDX")
	private int idx;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "LOOK")
	private String look; 
	@Column(name = "SEASON")
	private String season;
	@Transient
	@Column(name = "FASHION_FILE")
	private String fashionFile;
	
}

