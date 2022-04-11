package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {
	public static By getLocatorValue(String locatorValue) {
		String values[]=null;
		values=locatorValue.split(":");
		if(("XPATH").equalsIgnoreCase(values[0])){
			return By.xpath(values[1]);
		}
		else if(("CSS").equalsIgnoreCase(values[0])){
			return By.cssSelector(values[1]);
	}
		else if(("ID").equalsIgnoreCase(values[0])){
			return By.id(values[1]);
	}
		else if(("NAME").equalsIgnoreCase(values[0])){
			return By.name(values[1]);
	}
		else if(("CLASSNAME").equalsIgnoreCase(values[0])){
			return By.className(values[1]);
	}
		else if(("LINKTEXT").equalsIgnoreCase(values[0])){
			return By.linkText(values[1]);
	}
		else {
			System.out.println("Invalid Locator value");
			return null;
		}

}
	public static void waitForPageTOLoad(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	public static WebElement getElementUsingJsPath(WebDriver driver,String jsPath) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		String script="return "+jsPath.split(";")[1];
		WebElement element =(WebElement) js.executeScript(script);
		return element;
	}
	
	
	public static void getDataFromExcel() {
		
	}
}
