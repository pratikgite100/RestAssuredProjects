package com.aandhi.restAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aandhi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class RestTestcase1 extends TestBase {
	
	@BeforeTest
	void getAllEmployees() throws InterruptedException {
		//logger.info("***************************Started TC01_GetAllEmployees***********************************");
		
		RestAssured.baseURI = "https://gs-rel.qa-enphaseenergy.com/session-mgr/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/admin/signin/");
		
		response.prettyPrint();
		
		Thread.sleep(3000);
		
	}
	
	@Test
	void checkResponseBody() {
		//logger.info("***************************Checking Response Body***********************************");
		
		String responseBody = response.getBody().asString();
		//logger.info("Response Body ==>"+responseBody);
		Assert.assertTrue(responseBody !=null);
	}
	
	
	
	@Test
	void checkStatusCode() {
		//logger.info("***************************Checking StatusCode***********************************");
		
		int statusCode = response.getStatusCode();
		//logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkStatusLine() {
		
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 ");
		
	}
	
	
	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}
	
	
	@Test
	void checkTransferEncoding() {
		
		String transferEncoding = response.header("Transfer-Encoding");
		Assert.assertEquals(transferEncoding, "chunked");
	}
	
	

}
