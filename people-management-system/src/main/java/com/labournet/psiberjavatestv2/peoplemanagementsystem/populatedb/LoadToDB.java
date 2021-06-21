package com.labournet.psiberjavatestv2.peoplemanagementsystem.populatedb;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxRebate2020Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxRebate2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxThreshold2020Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxThreshold2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxYear2020Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxYear2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.excel.TaxYearData;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxRebate2020Service;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxRebate2021Service;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxThreshold2020Service;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxThreshold2021Service;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxYear2020Service;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxYear2021Service;

/*
 *  reading data from cache and storing to database
 *  
 * @author Nicky Mthembu
 *
 */
@Component
public class LoadToDB {
	private TaxYearData taxYearData;
	@Autowired
	private TaxYear2020Service taxYear2020Service;
	@Autowired
	private TaxYear2021Service taxYear2021Service;
	@Autowired
	private TaxRebate2020Service taxRebate2020Service;
	@Autowired
	private TaxRebate2021Service taxRebate2021Service;
	@Autowired
	private TaxThreshold2020Service taxThreshold2020Service;
	@Autowired
	private TaxThreshold2021Service taxThreshold2021Service;	
	//reading data from cache and storing to database
	@PostConstruct
	public void loadDB() throws IOException{
		
		taxYearData = new TaxYearData();
		Map<String, List<String[]>> taxYearMap = taxYearData.getTaxYearCollection();
		
		for(Entry<String, List<String[]>> col: taxYearMap.entrySet()){
			String cellName = col.getKey();
			Logger.getLogger("LoadToDB").info("cellName "+cellName);
			if(cellName.equals("Tax Year 2020")){
				List<String[]> list = col.getValue();
				populateTaxYear2020(list);	
				for(int i = 0; i < list.size(); i++) {
					String [] arr = list.get(i);
					Logger.getLogger("LoadToDB").info("cellName "+arr[0]);					
				}	
			}else if(cellName.equals("Tax Year 2021")){
				List<String[]> list = col.getValue();
				populateTaxYear2021(list);
			}else if(cellName.equals("Tax Rebates 2020")){
				List<String[]> list = col.getValue();
				populateTaxRebate2020(list);
				for(int i = 0; i < list.size(); i++) {
					String [] arr = list.get(i);
					Logger.getLogger("LoadToDB").info("cellName "+arr[0]);					
				}				
			}else if(cellName.equals("Tax Rebates 2021")){
				List<String[]> list = col.getValue();
				populateTaxRebate2021(list);
			}else if(cellName.equals("Tax Thresholds 2020")){ 
				List<String[]> list = col.getValue();
				populateTaxThreshold2020(list);
				for(int i = 0; i < list.size(); i++) {
					String [] arr = list.get(i);
					Logger.getLogger("LoadToDB").info("cellName "+arr[0]);					
				}				
			}else if(cellName.equals("Tax Thresholds 2021")){
				List<String[]> list = col.getValue();
				populateTaxThreshold2021(list);
			}
		}

	}
	
	//populating data store
	private void populateTaxRebate2020(List<String[]> list){
		for(int i = 1; i < list.size(); i++){
			String[] data = list.get(i);
			TaxRebate2020Entity entity = new TaxRebate2020Entity();
			entity.setRebate(data[0]);
			entity.setAmount(data[1].replace(" ", "")); 
			entity.setAge(data[2].replace(".0", ""));
			taxRebate2020Service.createOrUpdateTaxYear(entity);
		}
		
	}
	private void populateTaxRebate2021(List<String[]> list){
		for(int i = 1; i < list.size(); i++){
			String[] data = list.get(i);
			TaxRebate2021Entity entity = new TaxRebate2021Entity();
			entity.setRebate(data[0]);
			entity.setAmount(data[1].replace(" ", ""));
			entity.setAge(data[2].replace(".0", ""));
			taxRebate2021Service.createOrUpdateTaxYear(entity);
		}
		
	}

	private void populateTaxThreshold2020(List<String[]> list){
		for(int i = 1; i < list.size(); i++){
			String[] data = list.get(i);
			TaxThreshold2020Entity entity = new TaxThreshold2020Entity();
			entity.setInitialAge(data[0].replace(".0", ""));
			entity.setEndAge(data[1].replace(".0", ""));
			entity.setTaxThreshold(data[2].replace(" ", ""));
			taxThreshold2020Service.createOrUpdateTaxYear(entity);
		}	
	}
	private void populateTaxThreshold2021(List<String[]> list){
		for(int i = 1; i < list.size(); i++){
			String[] data = list.get(i);
			TaxThreshold2021Entity entity = new TaxThreshold2021Entity();
			entity.setInitialAge(data[0].replace(".0", ""));
			entity.setEndAge(data[1].replace(".0", ""));
			entity.setTaxThreshold(data[2].replace(" ", ""));
			taxThreshold2021Service.createOrUpdateTaxYear(entity);
		}	
	}
	
	private void populateTaxYear2020(List<String[]> list){
		for(int i = 1; i < list.size(); i++){
			String[] data = list.get(i);
			TaxYear2020Entity entity = new TaxYear2020Entity();
			entity.setInitialIncome(data[0].replace(" ", ""));
			entity.setEndIncome(data[1].replace(" ", ""));
			entity.setTaxRate(data[2].replace(" ", ""));
			entity.setTaxRatePercentage(data[3].replace(".0", ""));
			taxYear2020Service.createOrUpdateTaxYear(entity);
		}			
	}
	
	private void populateTaxYear2021(List<String[]> list){
		for(int i = 1; i < list.size(); i++){
			String[] data = list.get(i);
			TaxYear2021Entity entity = new TaxYear2021Entity();
			entity.setInitialIncome(data[0].replace(" ", ""));
			entity.setEndIncome(data[1].replace(" ", ""));
			entity.setTaxRate(data[2].replace(" ", ""));
			entity.setTaxRatePercentage(data[3].replace(".0", ""));
			taxYear2021Service.createOrUpdateTaxYear(entity);
		}			
	}	

}
