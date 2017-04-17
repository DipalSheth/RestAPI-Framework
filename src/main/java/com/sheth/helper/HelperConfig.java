package com.sheth.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;



public  class HelperConfig {
	private static Logger logger=Logger.getLogger(HelperConfig.class);
	public static String  endpoint ;
	public static String  consumerKey;
	public static String  consumerSecret;
	public static String  tokenkey;
	public static String  tokensecret;
	
	public static  void getConfig(String filename){
		Properties prop=new Properties();
		
		try {
			prop.load(new FileInputStream(filename));
			 logger.info("Readig data from config  file");
			endpoint=prop.getProperty("endpoint");
			consumerKey=prop.getProperty("consumerKey");
			consumerSecret=prop.getProperty("consumerSecret");
			tokenkey=prop.getProperty("tokenkey");
			tokensecret=prop.getProperty("tokensecret");
			
		} catch (FileNotFoundException e) {
			 logger.error("File not found - please give valid file name");
		} catch (IOException e) {
			 logger.error("Issue in reading given config properties file");
		}
	}
}
