
package com.labournet.psiberjavatestv2.peoplemanagementsystem.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * This class get income tax xlsx file diretory
 * 
 * @author Nicky Mthembu
 *
 */
public class ExcelFile {
	private final Logger logger = LoggerFactory.getLogger(ExcelFile.class);	
	private static final String PROP_FILE = "classpath:application.properties";

	//get xlsx file
	private InputStream getFile(){		
		FileInputStream fis = null;
		try{			
			fis = new FileInputStream(ResourceUtils.getFile(getPath())); 
		}catch(IOException io){
			logger.info("Xlsx file error :"+io.getMessage());			
		}

		return fis;
	}
	
	//get property file
	private String getPath() {
		String directory ="";
		try{			
			Properties prop = new Properties();
			prop.load(new FileInputStream(ResourceUtils.getFile(PROP_FILE)));
			directory = prop.getProperty("tax.income.xlsx.file");
		}catch(IOException io) {
			logger.info("Property file error :"+io.getMessage());
		}		
		return directory;
	}
	
	
	public InputStream getExcelFile(){
		return getFile();
	}

}