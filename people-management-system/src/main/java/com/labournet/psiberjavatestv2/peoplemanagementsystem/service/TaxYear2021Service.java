package com.labournet.psiberjavatestv2.peoplemanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxYear2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.repository.TaxYear2021Repository;

@Service
public class TaxYear2021Service {
	@Autowired
	private TaxYear2021Repository taxYearRepo;
	
	public void createOrUpdateTaxYear(TaxYear2021Entity entity){
		taxYearRepo.save(entity);	
	}
	public List<TaxYear2021Entity> list(){
		return taxYearRepo.findAll();
	}

}
