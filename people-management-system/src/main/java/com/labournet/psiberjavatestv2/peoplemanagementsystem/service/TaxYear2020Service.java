package com.labournet.psiberjavatestv2.peoplemanagementsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxYear2020Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.repository.TaxYear2020Repository;

@Service
public class TaxYear2020Service {
	@Autowired
	private TaxYear2020Repository taxYearRepo;
	
	public void createOrUpdateTaxYear(TaxYear2020Entity entity){
		taxYearRepo.save(entity);	
	}
	
	public List<TaxYear2020Entity> list(){
		return taxYearRepo.findAll();
	}

}
