package com.ata.job.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ata.job.model.DataFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FilterSearch {
	
	List<DataFilter> dataFilter;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public List<DataFilter> getFilterList(List<String> dataFilterArr) throws JsonMappingException, JsonProcessingException{
		dataFilter = new ArrayList<DataFilter>();
		for (String string : dataFilterArr) {
			DataFilter filter = objectMapper.readValue(string, DataFilter.class);
			dataFilter.add(filter);
		}
		return dataFilter;
	}
	
	private boolean filterStringValue(String value,DataFilter dataFilter) {
		boolean ret = false;
		switch (dataFilter.getCondition()) {
		case "eq":
			ret = value.equalsIgnoreCase(dataFilter.getText1())? ret = true : false; 
			break;
		case "c":
			ret = value.contains(dataFilter.getText1())? ret = true : false; 
			break;
		case "bw":
			ret = value.startsWith(dataFilter.getText1())? ret = true : false; 
			break;
		case "ew":
			ret = value.endsWith(dataFilter.getText1())? ret = true : false; 
			break;
		default:
			ret = false;
			break;
		}
		return ret;
	}
	
	private boolean filterDoubleValue(double value,DataFilter dataFilter) {
		boolean ret = false;
		double value1 = 0; double value2 = 0;
		if(!dataFilter.getText1().isEmpty()) {
			value1 = Double.parseDouble(dataFilter.getText1()) ;
		}
		if(dataFilter.getCondition().equals("bt") && !dataFilter.getText2().isEmpty()) {
			value2 = Double.parseDouble(dataFilter.getText2()) ;
		}
		switch (dataFilter.getCondition()) {
		case "eq":
			ret = value == value1? ret = true : false; 
			break;
		case "gt":
			ret = value>value1? ret = true : false; 
			break;
		case "lt":
			ret = value<value1? ret = true : false; 
			break;
		case "geq":
			ret = value>=value1? ret = true : false; 
			break;
		case "leq":
			ret = value<=value1? ret = true : false; 
			break;
		case "bt":
			ret = value > value1 && value < value2? ret = true : false; 
			break;
		default:
			ret = false;
			break;
		}
		return ret;
	}
	
	public boolean filterValue(String value,DataFilter dataFilter) {
		boolean ret = false;
		if(!dataFilter.getCondition().isEmpty() && !value.isEmpty()) {
			if(dataFilter.getType().equals("string")) {
				ret = this.filterStringValue(value, dataFilter);
			}else {
				double decimalvalue = Double.parseDouble(value);
				ret = this.filterDoubleValue(decimalvalue, dataFilter);
			}
		}
		return ret;
	}

}
