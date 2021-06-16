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

@SequenceGenerator(
        name="FASHIONFILE_SEQ_GEN",
        sequenceName="FASHIONFILE_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="fashionFile")
@Getter @Setter
public class FashionFileVO {
	
	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="FASHIONFILE_SEQ_GEN"        
            )
	@Column(name= "FILE_IDX")
	private int fileIdx;
	@Column(name= "FASHION_IDX")
	private int fashionIdx;
	@Column(name= "ORIG_FILE_NAME")
    private String origFileName;
	@Column(name= "FILE_NAME")
    private String fileName;
	@Column(name= "FILE_PATH")
    private String filePath;
    

    
}
