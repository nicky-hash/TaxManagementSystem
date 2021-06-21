package com.labournet.psiberjavatestv2.peoplemanagementsystem.entity;

public class CalculatorOutput {
	private Long id;
	private String paye;
	private String taxCredit;
	private String payeDue;
	private String netCash;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaye() {
		return paye;
	}
	public void setPaye(String paye) {
		this.paye = paye;
	}
	public String getTaxCredit() {
		return taxCredit;
	}
	public void setTaxCredit(String taxCredit) {
		this.taxCredit = taxCredit;
	}
	public String getPayeDue() {
		return payeDue;
	}
	public void setPayeDue(String payeDue) {
		this.payeDue = payeDue;
	}
	public String getNetCash() {
		return netCash;
	}
	public void setNetCash(String netCash) {
		this.netCash = netCash;
	}
	@Override
	public String toString() {
		return "CalculatorOutput [id=" + id + ", paye=" + paye + ", taxCredit="
				+ taxCredit + ", payeDue=" + payeDue + ", netCash=" + netCash
				+ "]";
	}
	
	
	
}
