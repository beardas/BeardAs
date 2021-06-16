package com.RanReco.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SequenceGenerator(
        name="BOARD_FILE_SEQ_GEN",
        sequenceName="BOARD_FILE_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="boardFile")
@Getter @Setter
public class BoardFileVO {
	
	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="BOARD_FILE_SEQ_GEN"        
            )
	private int fileIdx;
	private int boardIdx;
    private String locationFileName;
    private String locationFilePath;
    private String foodFileName;
    private String foodFilePath;
    private String fashionFileName;
    private String fashionFilePath;
    

    
}