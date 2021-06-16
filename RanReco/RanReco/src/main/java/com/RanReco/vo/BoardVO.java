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
        name="BOARD_SEQ_GEN",
        sequenceName="BOARD_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="BOARD")
@Getter @Setter
public class BoardVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="BOARD_SEQ_GEN"        
            )
	@Column(name = "IDX")
	private int idx;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "WRITER")
	private String writer;
	@Column(name = "CONTENT", length = 2000)
	private String content;
	@Column(name = "CNT")
	private int cnt;
	@Column(name = "REPLY_CNT")
	private int replyCnt;
	@Column(name = "LIKES")
	private int likes;
	@Column(name = "HATES")
	private int hates;
	@Column(name = "TAG")
	private String tag;
	@Column(name = "REG_DATE")
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();
	
	@Transient
	private String boardLike;
	@Transient
	private String locationFile;
	@Transient
	private String foodFile;
	@Transient
	private String fashionFile;
	
}

