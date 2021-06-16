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
        name="REPLY_SEQ_GEN",
        sequenceName="REPLY_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="REPLY")
@Getter @Setter
public class ReplyVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="REPLY_SEQ_GEN"        
            )
	@Column(name = "IDX")
	private int idx;
	@Column(name = "BOARD_IDX")
	private int boardIdx;
	@Column(name = "MEMBER_IDX")
	private int memberIdx;
	@Column(name = "WRITER")
	private String writer;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "REG_DATE")
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();
	@Column(name = "LIKES_CNT")
	private int likesCnt;
	@Column(name = "HATES_CNT")
	private int hatesCnt;
	
	@Transient
	private String reply_like;
	
}

