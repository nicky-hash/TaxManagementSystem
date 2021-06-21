package com.labournet.psiberjavatestv2.peoplemanagementsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TBL_TAX_YEAR_2020")
public class TaxYear2020Entity {
	@Id
	@GeneratedValue
	private Long id;
	private String initialIncome;
	private String endIncome;
	private String taxRate;
	private String taxRatePercentage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInitialIncome() {
		return initialIncome;
	}
	public void setInitialIncome(String initialIncome) {
		this.initialIncome = initialIncome;
	}
	public String getEndIncome() {
		return endIncome;
	}
	public void setEndIncome(String endIncome) {
		this.endIncome = endIncome;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getTaxRatePercentage() {
		return taxRatePercentage;
	}
	public void setTaxRatePercentage(String taxRatePercentage) {
		this.taxRatePercentage = taxRatePercentage;
	}
	

}
