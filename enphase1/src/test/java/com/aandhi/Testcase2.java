package com.aandhi;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aandhi.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase2 {
	
	@SuppressWarnings("deprecation")
	@Test(dataProvider = "empdataprovider2")
	public  void signUpUser(String fullName,String phone,String email, String accNumber, String address, String city, String state, String websiteURL) throws IOException, InterruptedException {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\xpath.properties");
		Properties prop = new Properties();
		prop.load(file);
		String email_id = prop.getProperty("email_id");
		String password1 = prop.getProperty("password");
		String btn_signIn =prop.getProperty("btn_signin");
		String txt_signIn = prop.getProperty("txt_signIn");
		String txt_incorrectPassword =prop.getProperty("txt_incorrectPassword");
		String txt_welcome= prop.getProperty("txt_welcome");
		String btn_signUp = prop.getProperty("btn_signUp");
		
		
		String txt_signUp = prop.getProperty("txt_signUp");
		String input_fullName = prop.getProperty("input_fullName");
		String input_phone = prop.getProperty("input_phone");
		String input_email = prop.getProperty("input_email");
		String input_accountName = prop.getProperty("input_accountName");
		String input_address = prop.getProperty("input_address");
		String input_city = prop.getProperty("input_city");
		String input_state = prop.getProperty("input_state");
		String input_website = prop.getProperty("input_website");
		String input_checkbox = prop.getProperty("input_checkbox");
		String btn_signUp1 = prop.getProperty("btn_signUp1");
		String select_registeras = prop.getProperty("select_registeras");
		String option_aggregator = prop.getProperty("option_aggregator");
		String select_country = prop.getProperty("select_country");
		String option_country = prop.getProperty("option_country");
		String select_timeZone = prop.getProperty("select_timeZone");
		String option_timeZone = prop.getProperty("option_timeZone");
		String msg_succReg = prop.getProperty("msg_succReg");
		
		
		
		 ChromeDriver driver = new ChromeDriver(); WebDriverWait wait = new
		 WebDriverWait(driver,Duration.ofSeconds(5));
		 WebDriverManager.chromedriver().setup();
		  
		  driver.get("https://grid-rel.qa-enphaseenergy.com/signin");
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  
		  
		  driver.findElement(By.xpath(btn_signUp)).click();  
		 
		driver.findElement(By.xpath(input_fullName)).sendKeys(fullName);
	    driver.findElement(By.xpath(input_phone)).sendKeys(phone);
	    driver.findElement(By.xpath(input_email)).sendKeys(email);
	    driver.findElement(By.xpath(input_accountName)).sendKeys(accNumber);
	    
	    driver.findElement(By.xpath(select_registeras)).click();
	    driver.findElement(By.xpath(option_aggregator)).click();
	    
	    
	    driver.findElement(By.xpath(input_address)).sendKeys(address);
	    driver.findElement(By.xpath(input_city)).sendKeys(city);
	    
	    driver.findElement(By.xpath(select_country)).click();
	    driver.findElement(By.xpath(option_country)).click();
	    
	    driver.findElement(By.xpath(input_state)).sendKeys(state);
	    driver.findElement(By.xpath(input_website)).sendKeys(websiteURL);
	    
	    
	    
	    driver.findElement(By.xpath(select_timeZone)).click();
	    driver.findElement(By.xpath(option_timeZone)).click();
	    
	    
	    
	    driver.findElement(By.xpath(input_checkbox)).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath(btn_signUp1)).click();
	    Thread.sleep(1000);
	    
	    String registereSuccessfullMsg = driver.findElement(By.xpath(msg_succReg)).getText();
    	Assert.assertEquals(registereSuccessfullMsg, "Successfully Registered. Given email address will receive email once approved.");
    	System.out.println("New User is successfully sign up...");
    	//Assert.assertEquals(registereSuccessfullMsg, null)
	    
	  /*  
	    if(isDisplayed(driver.findElements(By.xpath(txt_incorrectPassword)))) {
	    	//System.out.println("______________________________________________________");
		    String incorrectTxtMsg = driver.findElement(By.xpath(txt_incorrectPassword)).getText();
	    	Assert.assertEquals(incorrectTxtMsg, "Incorrect email ID or password");
	    	System.out.println("Incorrect email id or password, error message is verified successfully");
	    	
	    	driver.findElement(By.xpath(btn_signUp)).click();
	    	
	    }
	    
	    Thread.sleep(5000);
	    if(isDisplayed(driver.findElements(By.xpath(txt_welcome)))) {
	    	//System.out.println("___________________________________________________________");
	    	System.out.println("Successfully Log in");
	    	System.out.println();
	   }
	 
	 */   
	 
}
	
	@DataProvider(name = "empdataprovider2")
	String[][] getEmpData() throws NullPointerException{ 
		
		 ExcelReader excel = new ExcelReader("C:\\Users\\141572\\eclipse-workspace\\New Microservices projects\\enphase1\\src\\test\\resources\\LoginData.xlsx");
			
		   int rownum = excel.getRowCount("SignUpData");
		   int colcount = excel.getColumnCount("SignUpData");
			
		   //System.out.println("*******************"+rownum+"**********************"+colcount+"**********************************");
		   String empData[][] = new String[rownum-1][colcount];
			
		   for(int i=2;i<=rownum;i++) {
		     for(int j=0;j<colcount;j++) {
		    	empData[i-2][j] = excel.getCellData("SignUpData",j,i);
					
			 }
		   }
			
		   return empData;
		}
	
	
	public boolean isDisplayed(List<WebElement> list) {
        try {
        	boolean p=list.size()>0;
            return p;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
