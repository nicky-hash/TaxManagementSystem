package com.labournet.psiberjavatestv2.peoplemanagementsystem.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.calculator.Calculator2020;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.calculator.Calculator2021;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.CalculatorInput;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.CalculatorOutput;

@RestController
public class TaxCalculatorController{		
	
	@Autowired
	private Calculator2021 cal2021;
	@Autowired
	private Calculator2020 cal2020;
	private static final String PERIOD_2020 = "2020";
	private static final String PERIOD_2021 = "2021";
	
	@GetMapping("/calculator")
	@CrossOrigin(origins="http://localhost:4200")	
	public CalculatorOutput getCalculator(@RequestParam("age") String age, @RequestParam("taxYear") String taxYear,
			@RequestParam("taxableAmount") String taxableAmount, @RequestParam("medMembers") String medMembers,
			@RequestParam("period") String period){
		Logger.getLogger("TaxCalculatorController").info("Results "+age+" "+taxYear+" "+taxableAmount+" "+medMembers+" "+period);
		return getResults(age, taxYear,taxableAmount, medMembers, period);
	}
	
	private CalculatorOutput getResults(String age, String taxYear, String taxableAmount, String medMembers,String period){
		CalculatorOutput out = null;
		CalculatorInput input = new CalculatorInput();
		input.setAge(Integer.valueOf(age));
		input.setTaxYear(Integer.valueOf(taxYear));
		input.setTaxableAmount(Double.valueOf(taxableAmount));
		input.setMedMembers(Integer.valueOf(medMembers));
		input.setPeriod(period);
		
		if(taxYear.equals(PERIOD_2020))
			out = cal2020.getTaxCalculatorOutput(input);			
		
		if(taxYear.equals(PERIOD_2021))
			out = cal2021.getTaxCalculatorOutput(input);		
	
		return out;
	}
	
	

}
