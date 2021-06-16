package com.RanReco.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="LIKES")
@Getter @Setter
public class LikeVO {
	@Id
	private String idx;
	@Column(name = "REPLY_IDX")
	private int replyIdx;
	@Column(name = "BOARD_IDX")
	private int boardIdx;
	@Column(name = "MEMBER_IDX")
	private int memberIdx;
	@Column(name = "LIKES")
	private String Likes;
	
}

