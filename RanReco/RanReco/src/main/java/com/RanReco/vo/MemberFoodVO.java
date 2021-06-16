package com.RanReco.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEMBERFOOD")
@Getter @Setter
public class MemberFoodVO {
		@Id
		@Column(name = "MEMBER_IDX")
		private int memberIdx;
	    @Column(name = "MEMBER_FOOD")
	    private String memberFood;
	    @Column(name = "MEMBER_FOOD_FILE")
	    private String memberFoodFile;
	    
}
