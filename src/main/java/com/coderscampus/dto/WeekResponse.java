package com.coderscampus.dto;

import java.util.Map;

public class WeekResponse {

	private Map<String, Day> week;
	
	public Map<String, Day> getWeek() {
		return week;
	}
	
	public void setWeek(Map<String, Day> week) {
		this.week=week;
	}

	@Override
	public String toString() {
		return "WeekResponse [week=" + week + "]";
	}
	
}
