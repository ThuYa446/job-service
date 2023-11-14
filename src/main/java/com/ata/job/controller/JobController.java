package com.ata.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ata.job.dto.JobData;
import com.ata.job.model.Job;
import com.ata.job.service.JobService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/job_data")
@Slf4j
public class JobController {
	
	@Autowired
	JobData jobData;
	
	@Autowired
	JobService jobService;
	
	@GetMapping
	public JobData JobList() {
		List<Job> jobList = jobService.getJobList();
		Job[] jobArr = new Job[jobList.size()];
		jobArr = jobList.toArray(jobArr);
		jobData.setJobList(jobArr);
		jobData.setResponseMessage("Get Job List");
		return jobData;
	}

	@GetMapping("/job_search")
	public JobData getJobList(@RequestParam(value="jobTitle" , required=false) String jobTitle,
			@RequestParam(value="gender" , required=false) String gender,
			@RequestParam(value="salary" , required=false) String salary) {
		log.info(""+jobTitle +" -> " +salary+" -> " +gender);
		try {
			List<Job> filterJobList = jobService.dataFilter(jobTitle, gender, salary);
			if(!filterJobList.isEmpty()) {
				Job[] jobArr = new Job[filterJobList.size()];
				jobArr = filterJobList.toArray(jobArr);
				jobData.setJobList(jobArr);
				log.info("Filtered Job List -> "+jobData.getJobList());
				jobData.setResponseMessage("Job List Fetched Successfully.");
			}else {
				jobData.setResponseMessage("0014");
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobData;
	}
}
