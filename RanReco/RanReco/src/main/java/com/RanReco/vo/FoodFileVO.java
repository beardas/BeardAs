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
        name="FOODFILE_SEQ_GEN",
        sequenceName="FOODFILE_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="foodFile")
@Getter @Setter
public class FoodFileVO {
	
	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="FOODFILE_SEQ_GEN"        
            )
	@Column(name= "FILE_IDX")
	private int fileIdx;
	@Column(name= "FOOD_IDX")
	private int foodIdx;
	@Column(name= "ORIG_FILE_NAME")
    private String origFileName;
	@Column(name= "FILE_NAME")
    private String fileName;
	@Column(name= "FILE_PATH")
    private String filePath;
    

    
}
