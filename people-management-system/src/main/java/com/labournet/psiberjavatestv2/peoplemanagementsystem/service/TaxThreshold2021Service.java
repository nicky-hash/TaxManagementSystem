package com.labournet.psiberjavatestv2.peoplemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxThreshold2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.repository.TaxThreshold2021Repository;

@Service
public class TaxThreshold2021Service {
	@Autowired
	private TaxThreshold2021Repository taxYearRepo;
	
	public void createOrUpdateTaxYear(TaxThreshold2021Entity entity){
		taxYearRepo.save(entity);	
	}
	public List<TaxThreshold2021Entity> list(){
		return taxYearRepo.findAll();
	}

}
