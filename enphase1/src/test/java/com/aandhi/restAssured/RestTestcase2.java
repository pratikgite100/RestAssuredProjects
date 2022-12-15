package com.aandhi.restAssured;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aandhi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestTestcase2 extends TestBase {
	
	RequestSpecification requestSpecification;
	Response response;
	
	
	
	@BeforeTest
	void createEmployee() throws InterruptedException {
		
		RestAssured.baseURI = "https://gs-rel.qa-enphaseenergy.com/session-mgr/api/v1/admin";
		httpRequest= RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name","dgami22197@gmail.com");
		requestParams.put("password","Dhruvit@22");
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/signin/");
		//response.prettyPrint();
		
		Thread.sleep(3000);
	}
	
	
	@Test
	void checkResponseBody() {
		//logger.info("--------Checking ResponseBody--------");
		String responseBody = response.getBody().asString();
		//logger.info("Response Body ==>"+responseBody);
		Assert.assertEquals(responseBody.contains("Dhruvit Gami"), true);
		System.out.println();
		System.out.println();
	}
	
	@Test
	void checkStatusCode() {
		logger.info("--------Checking StatusCode--------");
		
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
		System.out.println();
		System.out.println();
	}
	
	@Test
	void checkStatusLine() {
		logger.info("--------Checking StatusLine--------");
		String statusLine = response.getStatusLine();
		logger.info("Satus Line ==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		System.out.println();
		System.out.println();
	}
	
	

	
	
	@Test
	void checkContentEncoding() {
		logger.info("--------Checking COntentEncoding--------");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Satus Line ==>"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		System.out.println();
		System.out.println();
		
	}
	
	
	

}
