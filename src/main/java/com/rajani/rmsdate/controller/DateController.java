package com.rajani.rmsdate.controller;

import java.text.ParseException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rajani.rmsdate.form.DateForm;
import com.rajani.rmsdate.model.RMSDate;

@Controller
public class DateController {

	//Initialize all application properties value
	@Value("${infoMessage}")
	private String infoMessage;

	@Value("${errorMessageMandatory}")
	private String errorMessageMandatory;

	@Value("${errorMessageDiscrepancyCheck}")
	private String errorMessageDiscrepancyCheck;

	@Value("${daysInfo}")
	private String daysInfo;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String showHomePage(Model model) {
		model.addAttribute("message", infoMessage);
		DateForm dateForm = new DateForm();
		model.addAttribute("dateForm", dateForm);
		return "index";
	}

	@RequestMapping(value = { "/index" }, method = RequestMethod.POST)
	public String evaluateDate(Model model, //
			@ModelAttribute("dateForm") DateForm dateForm) {
		model.addAttribute("message", infoMessage);
		//Check for mandatory values
		if (dateForm.getStartDate() == "" || dateForm.getEndDate() == "") {
			model.addAttribute("errorMessageMandatory", errorMessageMandatory);
			return "index";
		} else {
			LocalDate startDate = LocalDate.parse(dateForm.getStartDate());
			LocalDate endDate = LocalDate.parse(dateForm.getEndDate());
			
			//Check for end date after start date -Positive scenario to check days in between two dates
			if (endDate.isAfter(startDate)) {
				RMSDate rmsDate = new RMSDate(startDate, endDate);
			//Calculate the days in between by calling the method
				long days = rmsDate.difference(startDate, endDate);
			//Setting the message to be displayed with days value
				model.addAttribute("daysInfo", daysInfo + days);
			} 
			//Check if end date is before start date
			else if (endDate.isBefore(startDate)) {
				model.addAttribute("errorMessageDiscrepancyCheck", errorMessageDiscrepancyCheck);
			}
			return "index";
		}
	}
}
