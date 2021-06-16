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

import lombok.Getter;
import lombok.Setter;

@SequenceGenerator(
        name="NOTICE_SEQ_GEN",
        sequenceName="NOTICE_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="NOTICE")
@Getter @Setter
public class NoticeVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="NOTICE_SEQ_GEN"        
            )
	private int idx;
	private String title;
	private String writer;
	@Column(name = "CONTENT", length = 2000)
	private String content;
	private int cnt;
	
	
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();


	
	}

