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

@Entity
@Table(name = "MEMBERFASHION")
@Getter @Setter
public class MemberFashionVO {
		@Id
		@Column(name = "MEMBER_IDX")
		private int memberIdx;
	    @Column(name = "LOOK")
	    private String look;
	    @Column(name = "SEASON")
	    private String season;
	    @Column(name = "GENDER")
	    private String gender;
		@Column(name = "MEMBER_FASHION_FILE")
		private String memberFashionFile;
}
