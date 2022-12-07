package regression;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import parameterization.TestParameterization;

public class TestCase1 {
	
	@BeforeTest
	public void createDBConn() {
		
		System.out.println("Creating db conn");
	}
	
	@AfterTest
	public void closeDBConn() {
		
		System.out.println("Closing DB Conn");
	}
	
	@BeforeMethod
	public void launchBrowser() {
		
		System.out.println("Launching browser");
	}
	
	
	@AfterMethod
	public void closeBrowser() {
		
		System.out.println("Closing the browser");
	}
	
	@Test(dataProviderClass=TestParameterization.class, dataProvider = "runMode")
	public void getRunMode(Hashtable<String,String> data) {
		System.out.println("__________Executing getRunMode test____________");
		if(data.get("RunMode")=="Y") {
			System.out.println(data.get("TestCases")+"------------"+data.get("RunMode"));
			//TestCase1.startEachTime(data.get("TestCases"));
		}
		
		
	}
	
	
	//@Test(dataProviderClass=TestParameterization.class, dataProvider = "getData")
	public static void startEachTime(Hashtable<String,String> data, String sheetName) {
		
		Set s=data.entrySet();
		Iterator itr=s.iterator();
		
		
		while(itr.hasNext()) {
			
			TestCase1.gettt((Map.Entry)itr.next());
		}
		System.out.println("Executing login test");
		
	}
	
	//@Test()
	public static void gettt(Map.Entry entry) {
		// TODO Auto-generated method stub
		
		
		System.out.println("__________"+entry.getKey()+"__________"+entry.getValue()+"__________");
		
		
	}
	
	
	
	
	
	

	@Test()
	public void doUserReg() {
		
		System.out.println("Executing User Reg test");
		
	}
	
	@Test()
	public void doLogin() {
		
		System.out.println("Executing login test");
		
	}
	

	
	
	

}
