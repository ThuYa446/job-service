package com.ata.job.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.job.model.DataFilter;
import com.ata.job.model.Gender;
import com.ata.job.model.Job;
import com.ata.job.service.JobService;
import com.ata.job.util.FilterSearch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private FilterSearch filterSearch;

	List<String> searchDataList;

	@Override
	public List<Job> getJobList() {
		// TODO Auto-generated method stub
		List<Job> jobList = new ArrayList<Job>();
		jobList.add(new Job("Developer", Gender.MALE, 20000.56));
		jobList.add(new Job("Senior Developer", Gender.FEMALE, 20000.56));
		jobList.add(new Job("Manager", Gender.MALE, 20000.56));
		jobList.add(new Job("HR Manager", Gender.FEMALE, 20000.56));
		jobList.add(new Job("Secretary", Gender.MALE, 20000.56));
		jobList.add(new Job("Assistance", Gender.FEMALE, 20000.56));
		jobList.add(new Job("DevOps", Gender.MALE, 20000.56));
		jobList.add(new Job("Scrum Master", Gender.FEMALE, 20000.56));
		jobList.add(new Job("Role Operator", Gender.MALE, 20000.56));
		jobList.add(new Job("Chairman", Gender.FEMALE, 20000.56));
		jobList.add(new Job("CEO", Gender.MALE, 20000.56));
		return jobList;
	}

	@Override
	public void getFilterValues(String jobTitle, String gender, String salary) {
		// TODO Auto-generated method stub
		searchDataList = new ArrayList<String>();
		if (jobTitle != null) {
			searchDataList.add(jobTitle);
		}
		if (gender != null) {
			searchDataList.add(gender);
		}
		if (salary != null) {
			searchDataList.add(salary);
		}
	}

	@Override
	public List<Job> dataFilter(String jobTitle, String gender, String salary)
			throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		this.getFilterValues(jobTitle, gender, salary);
		List<DataFilter> dataFilters = filterSearch.getFilterList(searchDataList);
		List<Job> jobList = this.getJobList();
		// log.info("Data Filter List " + " -> " + dataFilters.toString());
		List<Job> filterJob = new ArrayList<Job>();
		for (Job job : jobList) {
			Set<Boolean> checkValidate = new HashSet<Boolean>();
			for (DataFilter dataFilter : dataFilters) {
				if (!dataFilter.getCondition().isEmpty() && !dataFilter.getText1().isEmpty()) {
					if (dataFilter.getValue().equals("jobTitle")) {
						if (filterSearch.filterValue(job.getJobTitle(), dataFilter)) {
							checkValidate.add(true);
						} else {
							checkValidate.add(false);
						}
					}
					if (dataFilter.getValue().equals("gender")) {
						if (filterSearch.filterValue(job.getGender().toString(), dataFilter)) {
							checkValidate.add(true);
						} else {
							checkValidate.add(false);
						}
					}
					if (dataFilter.getValue().equals("salary")) {
						if (filterSearch.filterValue(String.valueOf(job.getSalary()), dataFilter)) {
							checkValidate.add(true);
						} else {
							checkValidate.add(false);
						}
					}
				}
			}
			if (!checkValidate.isEmpty() && checkValidate.size() == 1) {
				Boolean[] ret = checkValidate.toArray(new Boolean[checkValidate.size()]);
				if (ret[0] == true) {
					filterJob.add(job);
				}
			}
		}
		return filterJob;
	}

}
