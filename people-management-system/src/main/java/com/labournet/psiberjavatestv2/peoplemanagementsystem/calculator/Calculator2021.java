package com.labournet.psiberjavatestv2.peoplemanagementsystem.calculator;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.CalculatorInput;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.CalculatorOutput;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxRebate2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.entity.TaxYear2021Entity;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxRebate2021Service;
import com.labournet.psiberjavatestv2.peoplemanagementsystem.service.TaxYear2021Service;

/**
 * This class calculate 2021 Tax year
 * 
 * @author Nicky Mthembu
 *
 */
@Component
public class Calculator2021 {
	@Autowired
	private TaxYear2021Service taxYear2021Service;
	@Autowired
	private TaxRebate2021Service taxRebate2021Service;
	private static  final double MED_CONTR = 319;
	private static  final double MED_CONTR_ADD = 215;
	private static  final int ANNUAL = 12;
	private static  final int TAX_AGE_65 = 65;
	private static  final int TAX_AGE_75 = 75;	
	private static  final double TRESHOLD_BELOW = 79000.0;
	private static  final double TRESHOLD_BETWEEN = 122300.0;
	private static  final double TRESHOLD_ABOVE = 136750.0;
	private DecimalFormat decimalFormat = new DecimalFormat("R###,###,###.##");
	private boolean isTaxable = false;
	private String taxYear;
	
	//calculating the output from the input
	private CalculatorOutput taxCalculatorOut(CalculatorInput input){
				
		CalculatorOutput output = new CalculatorOutput();	
		double amountAnnualTaxable = input.getTaxableAmount();
		taxYear = input.getPeriod();
		String[] taxCredit = null;
		double[] cashPayeDue= null;;
		amountAnnualTaxable = amountAnnualTaxable * ANNUAL;
		
		if(taxYear.equals("Monthly")){						
			isTaxable = getThresholdAmnt(input.getAge(),amountAnnualTaxable);
			if(isTaxable == false)
				amountAnnualTaxable=amountAnnualTaxable/12;
				
			output.setPaye(payAsYouEarnMonthly(amountAnnualTaxable,input.getAge()));
			cashPayeDue = payeDue(amountAnnualTaxable,input.getAge(),input.getMedMembers());	
		}
		if(taxYear.equals("Annual")){					
			isTaxable = getThresholdAmnt(input.getAge(),amountAnnualTaxable); 
			output.setPaye(payAsYouEarnAnnual(amountAnnualTaxable));	
			cashPayeDue = payeDue(amountAnnualTaxable,input.getAge(),input.getMedMembers());	
		}			
		taxCredit = getTaxcredits(input.getAge());
		output.setTaxCredit(taxCredit[0]);	
			
		output.setPayeDue(decimalFormat.format(cashPayeDue[0]));
		output.setNetCash(decimalFormat.format(cashPayeDue[1]));			
		
		return output;
	}
	
	// who ever qualify for tax
	private boolean getThresholdAmnt(int taxableAge, double amount){	
					
			if(taxableAge < TAX_AGE_65){				
				if(amount > TRESHOLD_BELOW){					
					isTaxable = true;								
				}				
			}else if(taxableAge >= TAX_AGE_65 && taxableAge <= TAX_AGE_75){
				if(amount > TRESHOLD_BETWEEN){					
					isTaxable = true;
				}		
			}else if(taxableAge >= TAX_AGE_75){
				if(amount > TRESHOLD_ABOVE){					
					isTaxable = true;					
				}		
			}
	
		return isTaxable; 
	}
	
	//calculate PAYE annual
	private String payAsYouEarnAnnual(double taxableAmount){
		List<TaxYear2021Entity> taxYearList = taxYear2021Service.list();
		double amount = 0.0;
		if(isTaxable == true){
			for(TaxYear2021Entity entity : taxYearList){
				double initAmount = Double.valueOf(entity.getInitialIncome());
				double endAmount = Double.valueOf(entity.getEndIncome());				
				if(taxableAmount > initAmount  && taxableAmount < endAmount){
					int taxRatePec = Integer.valueOf(entity.getTaxRatePercentage());
					amount = Double.valueOf(entity.getTaxRate()) + (taxRatePec/100);					
					break;
				}
			}
		}		
		return decimalFormat.format(amount);			
	}
	
	//calculate PAYE monthly
	private String payAsYouEarnMonthly(double taxableAmount,int age){
		List<TaxYear2021Entity> taxYearList = taxYear2021Service.list();		
		double paye = taxableAmount;
		if(isTaxable == true){
			for(TaxYear2021Entity entity : taxYearList){
				double rate = Double.valueOf(entity.getTaxRate());
				double tax = Double.valueOf(entity.getTaxRatePercentage());
				double initAmount = Double.valueOf(entity.getInitialIncome());
				double endAmount = Double.valueOf(entity.getEndIncome());
				if(taxableAmount > initAmount  && taxableAmount < endAmount){
					if(age >= TAX_AGE_65 && age <= TAX_AGE_75){
						paye = (((taxableAmount - initAmount) * (tax/100))+ rate - new Double(23157)) / 12;	
					}else{
						paye = ((taxableAmount - initAmount) * (tax/100)+ rate) / 12;
					}					
					break;
				}
			}
		}

		return decimalFormat.format(paye);
	}
	
	// get Tax Credit
	private String[] getTaxcredits(int age){
		List<TaxRebate2021Entity> taxYearList = taxRebate2021Service.list();
		String[] rebateData = new String[2];
		TaxRebate2021Entity entity = null;
		
		if(age < TAX_AGE_65){	
			entity = taxYearList.get(0);
			rebateData[0] = entity.getRebate();
			rebateData[1] = entity.getAmount();
		}else if(age >= TAX_AGE_65 && age < TAX_AGE_75){
			entity = taxYearList.get(1);
			rebateData[0] = entity.getRebate();
			rebateData[1] = entity.getAmount();	
		}else if(age >= TAX_AGE_75){
			entity = taxYearList.get(2);
			rebateData[0] = entity.getRebate();
			rebateData[1] = entity.getAmount();	
		}
		
		return rebateData;
	}
	
	//calculate PAYEDue
	private double[] payeDue(double taxableAmount, int age, int dependencies){
		List<TaxYear2021Entity> taxYearList = taxYear2021Service.list();
		double totDed = totDeduction(dependencies);
		String[] rebate = getTaxcredits(age);
		double[] cashPayeDue = new double[2];		
		Double payeDue = taxableAmount;
		if(isTaxable == true){
			for(TaxYear2021Entity entity : taxYearList){
				double rate = Double.valueOf(entity.getTaxRate());			
				double initAmount = Double.valueOf(entity.getInitialIncome());
				double endAmount = Double.valueOf(entity.getEndIncome());
				if(taxableAmount > initAmount  && taxableAmount < endAmount){
					payeDue = 	((taxableAmount - initAmount) + rate - new Double(rebate[1]))/12;
					break;
				}
			}
			double netCashPay = (taxableAmount - totDed - payeDue);
			if(taxYear.equals("Monthly")){
				cashPayeDue[0] = payeDue/12;
				cashPayeDue[1] = netCashPay/12;
			}else{
				cashPayeDue[0] = payeDue;
				cashPayeDue[1] = netCashPay;				
			}
		}else{
			double netCashPay = taxableAmount - totDed;
			cashPayeDue[0] = 0.0;
			cashPayeDue[1] = netCashPay;			
		}			
		return cashPayeDue;
	}
	
	// calculating total deduction
	private double totDeduction(int dependencies){
		double totDeduction = 0.0;
		if(dependencies <= 2){
			totDeduction = MED_CONTR * dependencies;
		}else{
			double addContr = dependencies - 2;
			totDeduction = MED_CONTR_ADD * addContr + MED_CONTR * dependencies;
		}			
		return totDeduction;
	}
	
	public CalculatorOutput getTaxCalculatorOutput(CalculatorInput input){
		return taxCalculatorOut(input);
	}

}

