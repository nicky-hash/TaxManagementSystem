package com.labournet.psiberjavatestv2.peoplemanagementsystem.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 *  This arranges all cache data accordingly and them again
 *  
 * @author Nicky Mthembu
 *
 */
public class TaxYearData {
	private Logger log = LoggerFactory.getLogger(TaxYearData.class);
	private ExcelSheets excelSheet;
	private static final String Tax_Year_2020 ="Tax Year 2020";
	private static final String Tax_Year_2021 ="Tax Year 2021";
	private static final String Tax_Rebates_2020 ="Tax Rebates 2020";
	private static final String Tax_Rebates_2021 ="Tax Rebates 2021";
	private static final String Tax_Thresholds_2020 ="Tax Thresholds 2020";
	private static final String Tax_Thresholds_2021 ="Tax Thresholds 2021";
	private List<String[]> taxYearcellDataList;
	private Map<String,List<String[]>> taxYearMap;
	private String[] taxYearcellData;
	
	public TaxYearData(){
		excelSheet = new ExcelSheets();		
		taxYearMap = new HashMap<>();			
	}
	
	//normalizing  and caching data
	private Map<String, List<String[]>> createTaxYearCollection(){
		try{
			Iterator<XSSFSheet> sheetList = excelSheet.getSheetList();
			while(sheetList.hasNext()){
				Sheet sheet = sheetList.next();
				String sheetName = sheet.getSheetName();
				Iterator<Row> rowList = sheet.iterator(); 
				if(sheetName.equals(Tax_Year_2020)){					
					List<String[]> taxYearcellDataList =  taxYearcellData(rowList,4);
					taxYearMap.put(sheetName, taxYearcellDataList);
				}else if(sheetName.equals(Tax_Year_2021)){				 	
					List<String[]> taxYearcellDataList =  taxYearcellData(rowList,4);
					taxYearMap.put(sheetName, taxYearcellDataList);
				}else if(sheetName.equals(Tax_Rebates_2020)){				 	
					List<String[]> taxYearcellDataList =  taxYearcellData(rowList,3);
					taxYearMap.put(sheetName, taxYearcellDataList);
				}else if(sheetName.equals(Tax_Rebates_2021)){				 	
					List<String[]> taxYearcellDataList =  taxYearcellData(rowList,3);
					taxYearMap.put(sheetName, taxYearcellDataList);
				}else if(sheetName.equals(Tax_Thresholds_2020)){				 	
					List<String[]> taxYearcellDataList =  taxYearcellData(rowList,3);
					taxYearMap.put(sheetName, taxYearcellDataList);
				}else if(sheetName.equals(Tax_Thresholds_2021)){				 	
					List<String[]> taxYearcellDataList =  taxYearcellData(rowList,3);
					taxYearMap.put(sheetName, taxYearcellDataList);
				}
			}			
		}catch(Exception io){
			log.info("TaxYearData()"+io.getMessage());;
		}

		return taxYearMap;
	}
	//read cell value and caching it
	private List<String[]> taxYearcellData(Iterator<Row> rowList, int cellSize){
		taxYearcellDataList = new ArrayList<>();
		while(rowList.hasNext()){
			Row row = rowList.next();			
			Iterator<Cell> cellIterator = row.cellIterator();			
			int count = 0;
			taxYearcellData = new String[cellSize];
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {				
					case Cell.CELL_TYPE_STRING:						
						taxYearcellData[count] = cell.getStringCellValue();						
						count++;
						break;
					case Cell.CELL_TYPE_NUMERIC:		
						double d = cell.getNumericCellValue();
						taxYearcellData[count] = String.valueOf(d);						
						count++;
						break;						
				}				
			}
			taxYearcellDataList.add(taxYearcellData);
		}
		return taxYearcellDataList;
	}
	
	public Map<String, List<String[]>> getTaxYearCollection(){
		return createTaxYearCollection();
	}
		
}

