package com.RanReco.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEMBERLOCATION")
@Getter @Setter
public class MemberLocationVO {
		@Id
		@Column(name = "MEMBER_IDX")
		private int memberIdx;
	    @Column(name = "MEMBER_LOCATION")
	    private String memberLocation;
	    @Column(name = "MEMBER_LOCATION_FILE")
	    private String memberLocationFile;
	    
	    
	    @Transient
	       private String sido;
	       @Transient
	       private String sigungu;
	       @Transient
	       private String dong;
}
