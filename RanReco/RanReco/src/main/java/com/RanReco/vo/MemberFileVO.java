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
        name="MEMBER_FILE_SEQ_GEN",
        sequenceName="MEMBER_FILE_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="MEMBER_FILE")
@Getter @Setter
public class MemberFileVO {
	
	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="MEMBER_FILE_SEQ_GEN"        
            )
	private int fileIdx;
	private int memberIdx;
    private String origFileName;
    private String fileName;
    private String filePath;
    
    

    
}