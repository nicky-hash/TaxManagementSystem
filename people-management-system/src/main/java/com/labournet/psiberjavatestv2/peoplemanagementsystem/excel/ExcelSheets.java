package com.labournet.psiberjavatestv2.peoplemanagementsystem.excel;

import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 *  This class cachesxlsx sheets
 *  
 * @author Nicky Mthembu
 *
 */
public class ExcelSheets {
	private Logger log = LoggerFactory.getLogger(ExcelSheets.class);
	private ExcelFile getExcelFile;
	
	private Iterator<XSSFSheet> sheets(){		
		//iterating over excel sheets
		Iterator<XSSFSheet> sheetList = null;
		try{
			sheetList = getWorkbook().iterator();			
		}catch(IOException ex){
			log.info("ExcelSheets "+ex.getMessage());
		}	
		return sheetList;
	}
	
	private XSSFWorkbook getWorkbook() throws IOException{
		XSSFWorkbook wb = null;
		getExcelFile = new ExcelFile();
		try{			
			//creating workbook instance that refers to .xls file  
			wb = new XSSFWorkbook(getExcelFile.getExcelFile());				
		}catch(IOException ex){
			log.info("ExcelSheets "+ex.getMessage());
		}	
		return wb;
	}
	
	public Iterator<XSSFSheet> getSheetList(){
		return sheets();
	}
	

}
