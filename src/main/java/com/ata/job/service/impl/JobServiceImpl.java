package com.ata.job.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.job.model.DataFilter;
import com.ata.job.model.Gender;
import com.ata.job.model.Job;
import com.ata.job.service.JobService;
import com.ata.job.util.FilterSearch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private FilterSearch filterSearch;
	
	private List<String> searchDataList;
	
	@Override
	public List<Job> getJobList() {
		// TODO Auto-generated method stub
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
		return jobList;
	}

	@Override
	public void getFilterValues(String jobTitle, String gender, String salary) {
		// TODO Auto-generated method stub
		searchDataList = new ArrayList<String>();
		if(jobTitle != null) {
			searchDataList.add(jobTitle);
		}
		if(gender != null) {
			searchDataList.add(gender);
		}
		if(salary != null) {
			searchDataList.add(salary);
		}
	}

	@Override
	public boolean dataFilter(String jobTitle, String gender, String salary)
			throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
			this.getFilterValues(jobTitle, gender, salary);
			List<DataFilter> dataFilters = filterSearch.getFilterList(searchDataList);
			List<Job> jobList = this.getJobList();
		
		return true;
	}
	


}
