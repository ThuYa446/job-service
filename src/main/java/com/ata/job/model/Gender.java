package com.ata.job.model;

public enum Gender {
	MALE("MALE"),FEMALE("FEMALE");
	
	private String gender;

	private Gender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString(){
        return gender;
    }
	
}
