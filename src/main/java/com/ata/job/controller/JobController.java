package com.ata.job.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ata.job.dto.JobData;
import com.ata.job.model.Gender;
import com.ata.job.model.Job;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/job_data")
@Slf4j
public class JobController {
	
	@Autowired
	JobData jobData;
	
	@GetMapping
	public JobData JobList() {
		List<Job> jobList = new ArrayList<Job>();
		jobList.add(new Job("Developer",Gender.Male,20000.56));
		jobList.add(new Job("Senior Developer",Gender.Male,20000.56));
		jobList.add(new Job("Manager",Gender.Male,20000.56));
		jobList.add(new Job("HR Manager",Gender.Male,20000.56));
		jobList.add(new Job("Secretary",Gender.Male,20000.56));
		jobList.add(new Job("Assistance",Gender.Male,20000.56));
		jobList.add(new Job("DevOps",Gender.Male,20000.56));
		jobList.add(new Job("Scrum Master",Gender.Male,20000.56));
		jobList.add(new Job("Role Operator",Gender.Male,20000.56));
		jobList.add(new Job("Chairman",Gender.Male,20000.56));
		jobList.add(new Job("CEO",Gender.Male,20000.56));
		
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
		List<Job> jobList = new ArrayList<Job>();
		
		Job[] jobArr = new Job[jobList.size()];
		
		jobArr = jobList.toArray(jobArr);
		jobData.setJobList(jobArr);
		jobData.setResponseMessage("Get Job List");
		return jobData;
	}
}
