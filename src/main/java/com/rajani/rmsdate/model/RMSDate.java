package com.rajani.rmsdate.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RMSDate {
	private LocalDate startDate;
	private LocalDate endDate;
	
	public RMSDate() {}
	
	public RMSDate(LocalDate startDate, LocalDate endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public long difference(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate);
	} 
	
}
