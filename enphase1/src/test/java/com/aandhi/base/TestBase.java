package com.aandhi.base;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TestBase {
	
	public static RequestSpecification httpRequest1;
	public static Response response1;
	
		
	
	public Logger logger = LogManager.getLogger(TestBase.class);
	
	
	

	
	@BeforeClass
	public void setup() throws SecurityException{
		
		logger= LogManager.getLogger(getClass());
		PropertyConfigurator.configure("Log4j.properties"); //added logger
		logger.setLevel(Level.DEBUG);
		BasicConfigurator.configure();
		//logger.info("_____________________________________________________________________");
	}
			

}
