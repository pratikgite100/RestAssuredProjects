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

public class Testcase1 {
	
	@SuppressWarnings("deprecation")
	@Test(dataProvider = "empdataprovider1")
	public  void login(String email,String password) throws IOException, InterruptedException {
		
		//Testcase2 t =new Testcase2();
		
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
		
		
		
	
		ChromeDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    WebDriverManager.chromedriver().setup();
	
	    driver.get("https://grid-rel.qa-enphaseenergy.com/signin");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath(email_id)).sendKeys(email);
	    driver.findElement(By.xpath(password1)).sendKeys(password);
	    driver.findElement(By.xpath(btn_signIn)).click();
	    
	    if(isDisplayed(driver.findElements(By.xpath(txt_incorrectPassword)))) {
	    	//System.out.println("______________________________________________________");
		    String incorrectTxtMsg = driver.findElement(By.xpath(txt_incorrectPassword)).getText();
	    	Assert.assertEquals(incorrectTxtMsg, "Incorrect email ID or password");
	    	System.out.println("Incorrect email id or password, error message is verified successfully");
	    	//t.signUpUser(password, password1, email_id, btn_signIn, txt_signIn, txt_incorrectPassword, txt_welcome, btn_signUp)
	    	
	    	driver.findElement(By.xpath(btn_signUp)).click();
	    	
	    }
	    
	    Thread.sleep(5000);
	    if(isDisplayed(driver.findElements(By.xpath(txt_welcome)))) {
	    	//System.out.println("___________________________________________________________");
	    	System.out.println("Successfully Log in");
	    	System.out.println();
	   }
	 
	    
	 
}
	
	@DataProvider(name = "empdataprovider1")
	String[][] getEmpData() throws NullPointerException{ 
		
		 ExcelReader excel = new ExcelReader("C:\\Users\\141572\\eclipse-workspace\\New Microservices projects\\enphase1\\src\\test\\resources\\LoginData.xlsx");
			
		   int rownum = excel.getRowCount("LoginData");
		   int colcount = excel.getColumnCount("LoginData");
			
		   //System.out.println("*******************"+rownum+"**********************"+colcount+"**********************************");
		   String empData[][] = new String[rownum-1][colcount];
			
		   for(int i=2;i<=rownum;i++) {
		     for(int j=0;j<colcount;j++) {
		    	empData[i-2][j] = excel.getCellData("LoginData",j,i);
					
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
