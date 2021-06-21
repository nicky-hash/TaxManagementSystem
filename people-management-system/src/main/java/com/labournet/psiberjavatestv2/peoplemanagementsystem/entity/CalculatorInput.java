package com.labournet.psiberjavatestv2.peoplemanagementsystem.entity;

public class CalculatorInput {
	private Long id;
	private int taxYear;
	private int age;
	private double taxableAmount;
	private int medMembers;
	private String period;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getTaxYear() {
		return taxYear;
	}
	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public int getMedMembers() {
		return medMembers;
	}
	public void setMedMembers(int medMembers) {
		this.medMembers = medMembers;
	}
	
	
	
}
