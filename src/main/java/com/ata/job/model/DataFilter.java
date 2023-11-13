package com.ata.job.model;

import lombok.Data;

@Data
public class DataFilter {
	
	private String caption;
	private String value;
	private String type;
	private boolean isBetween;
	private String text1;
	private String text2;
	private String sorting;

}
