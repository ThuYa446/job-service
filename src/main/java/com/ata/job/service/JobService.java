package com.ata.job.service;

import java.util.List;

import com.ata.job.model.Job;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface JobService {
	List<Job> getJobList();

	void getFilterValues(String jobTitle, String gender, String salary);
	
	boolean dataFilter(String jobTitle,String gender,String salary) throws JsonMappingException, JsonProcessingException;

}
