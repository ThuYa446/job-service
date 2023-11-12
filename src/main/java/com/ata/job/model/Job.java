package com.ata.job.model;

import lombok.Data;

@Data
public class Job {
	private String jobTitle;
	private Gender gender;
	private double salary;
	
	public Job(String jobTitle, Gender gender, double salary) {
		this.jobTitle = jobTitle;
		this.gender = gender;
		this.salary = salary;
	}
	
	
	
	
}
