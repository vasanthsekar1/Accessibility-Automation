package stepDefinition;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import static baseClass.AccessibilityBaseClass .*;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import runnerClass.TestRunner;


public class StepDefinition extends TestRunner{
	
	public static WebDriver driver;
	
	
	@Given("user is on the desired webpage={string} in the browser")
	public void user_is_on_the_desired_webpage_in_the_browser(String URL) {
		if(driver.getCurrentUrl()!=URL) {
		driver.get(URL);
		}
	}
	
	
	@BeforeAll()
	public static void before_all() {
		
		String browser=System.getenv("Browser");
		try {
			if(browser.equalsIgnoreCase("Chrome")) {
				ChromeOptions co=new ChromeOptions();
				co.addExtensions(new File(System.getProperty("user.dir")+"/src/test/resources/Accessible-Color-Picker.crx"));
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver(co);
				
			}
			else if(browser.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			else if(browser.equalsIgnoreCase("Safari")) {
				WebDriverManager.safaridriver().setup();
				driver=new SafariDriver();
				
			}
			else if(browser.equalsIgnoreCase("FireFox")) {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				
			}
			else {
				System.out.println("Invalid Browser Name");
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	    
	}

	@Then("check if the WebElement is having proper colorcontrast ratio={string}")
	public void check_if_the_given_element_having_proper_colorcontrast_or_not(String webElement){
		
		if(checkColourContrstOfButton(driver,webElement)){
			System.out.println("The Element is having proper ColorContrast ratio");
			Assert.assertEquals(true, true);
		}
		else {
			System.out.println("The Element is not having proper ColorContrast ratio");
			Assert.assertEquals(false, true);
		}
	    
	}
	@Then("check if the WebElement={string} is having proper link\\/button text={string}")
	public void check_if_the_web_element_is_having_proper_link_button_text(String locator, String text) {
		if(checkLinkOrButtonText(driver, locator, text)) {
			System.out.println("The Link/Button text is Matching");
			Assert.assertEquals(true, true);
		}
		else {
			System.out.println("The Link/Button text is Not Matching");
			Assert.assertEquals(false, true);
			
		}
		
	}
	
	@AfterAll
	public static void teardown() {
		
//		if (scenario.isFailed()) {
//            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png",scenario.getName().replace(" ","_"));    
//            
//        }
		driver.quit();
	}
}
