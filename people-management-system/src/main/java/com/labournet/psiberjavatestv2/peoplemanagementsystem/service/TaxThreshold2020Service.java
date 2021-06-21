package com.labournet.psiberjavatestv2.peoplemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxThreshold2020Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.repository.TaxThreshold2020Repository;

@Service
public class TaxThreshold2020Service {
	@Autowired
	private TaxThreshold2020Repository taxYearRepo;
	
	public void createOrUpdateTaxYear(TaxThreshold2020Entity entity){
		taxYearRepo.save(entity);	
	}
	public List<TaxThreshold2020Entity> list(){
		return taxYearRepo.findAll();
	}

}
