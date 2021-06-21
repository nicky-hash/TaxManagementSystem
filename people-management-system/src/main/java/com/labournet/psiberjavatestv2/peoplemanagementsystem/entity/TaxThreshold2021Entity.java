package com.labournet.psiberjavatestv2.peoplemanagementsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAX_THRESHOLD_2021")
public class TaxThreshold2021Entity {
	@Id
	@GeneratedValue
	private Long id;
	private String initialAge;
	private String endAge;
	private String taxThreshold;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInitialAge() {
		return initialAge;
	}
	public void setInitialAge(String initialAge) {
		this.initialAge = initialAge;
	}
	public String getEndAge() {
		return endAge;
	}
	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}
	public String getTaxThreshold() {
		return taxThreshold;
	}
	public void setTaxThreshold(String taxThreshold) {
		this.taxThreshold = taxThreshold;
	}
	
}
