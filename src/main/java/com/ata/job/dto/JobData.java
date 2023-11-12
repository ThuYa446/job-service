package com.ata.job.dto;

import org.springframework.stereotype.Component;

import com.ata.job.model.Job;

import lombok.Data;

@Data
@Component
public class JobData {

	private String responseMessage;
	private Job[] jobList;
}
