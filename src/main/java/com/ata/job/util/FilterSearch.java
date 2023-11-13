package com.ata.job.util;

import java.util.ArrayList;
import java.util.List;

import com.ata.job.model.DataFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilterSearch {
	
	private List<DataFilter> dataFilter;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public List<DataFilter> getFilterList(List<String> dataFilterArr) throws JsonMappingException, JsonProcessingException{
		dataFilter = new ArrayList<DataFilter>();
		for (String string : dataFilterArr) {
			DataFilter filter = objectMapper.readValue(string, DataFilter.class);
			dataFilter.add(filter);
		}
		return dataFilter;
	}

}
