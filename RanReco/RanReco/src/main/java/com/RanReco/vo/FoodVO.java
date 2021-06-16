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
        name="FOOD_SEQ_GEN",
        sequenceName="FOOD_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name = "FOOD")
@Getter @Setter
public class FoodVO {
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="FOOD_SEQ_GEN"        
            )
		@Column(name = "IDX")
		private int idx;
	    @Column(name = "STORE_NAME")
	    private String storeName;
	    @Column(name = "LOCATION")
	    private String location;
	    @Column(name = "ADDRESS")
	    private String address;
	    @Column(name = "STAR")
	    private String star;
	    @Column(name = "URL")
	    private String url;
	    @Transient
	    @Column(name = "FOOD_FILE")
	    private String foodFile;
	    
}
