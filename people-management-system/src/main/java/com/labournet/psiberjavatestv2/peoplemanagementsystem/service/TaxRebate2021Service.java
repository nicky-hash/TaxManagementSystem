package com.labournet.psiberjavatestv2.peoplemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxRebate2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.repository.TaxRebate2021Repository;

@Service
public class TaxRebate2021Service {
	@Autowired
	private TaxRebate2021Repository taxYearRepo;
	
	public void createOrUpdateTaxYear(TaxRebate2021Entity entity){
		taxYearRepo.save(entity);	
	}
	public List<TaxRebate2021Entity> list(){
		return taxYearRepo.findAll();
	}

}
