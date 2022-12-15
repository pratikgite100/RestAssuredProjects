package com.aandhi.restAssured;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aandhi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestTestcase3 extends TestBase {
	
	//RequestSpecification requestSpecification;
	//RequestSpecification httpRequest;
	//Response response;
	 String gs_authorization;
	
	
	@Test(priority=1)
	void signIn() throws InterruptedException {
		
		RestAssured.baseURI = "https://gs-rel.qa-enphaseenergy.com";
		RequestSpecification httpRequest= RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name","dgami22197@gmail.com");
		requestParams.put("password","Dhruvit@22");
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		Response response = httpRequest.request(Method.POST,"/session-mgr/api/v1/admin/signin/");
		response.prettyPrint();
		
		JsonPath jsonPathEvaluator = response.jsonPath();
	    gs_authorization = jsonPathEvaluator.get("GS-Authorization");
	}
	
	@Test(priority=2)
	void signOut() {
		 
		 System.out.println();
		 System.out.println(gs_authorization);
		 System.out.println();
		 
		 RestAssured.baseURI = "https://gs-rel.qa-enphaseenergy.com";
		 RestAssured.basePath = "/session-mgr/api/v1/admin/60cc6e48aa05fd40e0028e32/user/signout";
		 RequestSpecification httpRequest1= RestAssured.given();
		 
		 httpRequest1.queryParam("email","dgami22197@gmail.com");
		// httpRequest.header("GS-Authorization",gs_authorization);
		 Response response1 = httpRequest1.request(Method.GET);
		 
	     response1.prettyPrint();
		 System.out.println("####################   "+response1.getStatusCode());
	     System.out.println("______________  "+response1.getBody().asString());
	     
	     Assert.assertEquals(response1.getStatusCode(), 200);
	     
	     
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	
	
	
	
	//@Test
	void checkResponseBody() {
		//logger.info("--------Checking ResponseBody--------");
		String responseBody = response.getBody().asString();
		//logger.info("Response Body ==>"+responseBody);
		Assert.assertEquals(responseBody.contains("User dgami22197@gmail.com has been succesfully logged out"), true);
		System.out.println();
		System.out.println();
	}
	
	@Test(priority=3)
	void checkStatusCode() {
		logger.info("--------Checking StatusCode--------");
		
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
		System.out.println();
		System.out.println();
	}
	
	@Test(priority=4)
	void checkStatusLine() {
		logger.info("--------Checking StatusLine--------");
		String statusLine = response.getStatusLine();
		logger.info("Satus Line ==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		System.out.println();
		System.out.println();
	}
	
	

	
	
	@Test(priority=5)
	void checkContentEncoding() {
		logger.info("--------Checking COntentEncoding--------");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Satus Line ==>"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		System.out.println();
		System.out.println();
		
	}
*/
}
