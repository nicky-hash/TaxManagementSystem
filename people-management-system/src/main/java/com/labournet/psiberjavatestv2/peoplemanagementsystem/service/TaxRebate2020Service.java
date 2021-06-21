package com.labournet.psiberjavatestv2.peoplemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxRebate2020Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.repository.TaxRebate2020Repository;

@Service
public class TaxRebate2020Service {
	@Autowired
	private TaxRebate2020Repository taxYearRepo;
	
	public void createOrUpdateTaxYear(TaxRebate2020Entity entity){
		taxYearRepo.save(entity);	
	}
	public List<TaxRebate2020Entity> list(){
		return taxYearRepo.findAll();
	}
}
