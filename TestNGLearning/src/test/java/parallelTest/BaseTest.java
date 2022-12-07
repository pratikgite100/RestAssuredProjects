package parallelTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	
	
	public WebDriver driver;
	
	
	public WebDriver getDriver(String browser) {
		
		System.setProperty("webdriver.chrome.driver","C:\\Apps\\chromedriver_win32");
		//WebDriver driver = new ChromeDriver();
		
		if(browser.equals("chrome")) {
			
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("http://selenium.dev");
			System.out.println("driver title: "+driver.getTitle());
			driver.quit();
			
		}else if(browser.equals("firefox")) {
			
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	
		return driver;
	}

}
