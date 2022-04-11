package baseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import utils.Util;

public class AccessibilityBaseClass extends Util{
	public static Boolean checkColourContrstOfButton(WebDriver driver,String locatorValue) {
		WebElement element=driver.findElement(getLocatorValue(locatorValue));
		String elementColor=Color.fromString(element.getCssValue("color")).asHex();
		String backGroundColor=Color.fromString(element.getCssValue("background-color")).asHex();
		System.out.println("ForeGround color Hexacode "+elementColor);
		System.out.println("BackGround color Hexacode "+ backGroundColor);
		String colorPickerURL="chrome-extension://bgfhbflmeekopanooidljpnmnljdihld/app.html";
		driver.get(colorPickerURL);
		WebElement foreGround=driver.findElement(By.xpath("//*[@id='foreground-color-hex']"));
		foreGround.clear();
		foreGround.sendKeys(elementColor);
		WebElement backGround=driver.findElement(By.xpath("//*[@id='background-color-hex']"));
		backGround.clear();
		backGround.sendKeys(backGroundColor);
		String colorContrastRatio=driver.findElement(By.xpath("//*[@id=\'compliance\']/div/div[1]")).getText().toString();
		System.out.println("Color Contrast Ratio is "+colorContrastRatio);
		WebElement resultColorElement=driver.findElement(By.xpath("//*[@id=\'compliance\']/div/div[2]"));
		String resultColor=Color.fromString(resultColorElement.getCssValue("background-color")).asHex().toString();
		
		System.out.println(resultColor);
		waitForPageTOLoad(2);
		driver.navigate().back();
		if(("#68b915").contains(resultColor)) {
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean checkLinkOrButtonText(WebDriver driver,String locator,String text ) {
		Boolean arialabel=driver.findElement(getLocatorValue(locator)).getAttribute("aria-label")!=null?true:false;
		String linkText=driver.findElement(getLocatorValue(locator)).getText().toString();
		if(arialabel==true) {
			String arialabelofElement=driver.findElement(getLocatorValue(locator)).getAttribute("aria-label").toString();
			if(arialabelofElement.contains(text)) {
				System.out.println("Aria-label is Matching");
				return true;
			}
			else {
				System.out.println("Aria-label is Not Matching");
				return false;
				
			}
		}
		else if(linkText.contains(text)) {
			System.out.println("LinkText is Matching");
			return true;
		}
		else {
			System.out.println("LinkText is Not Matching");
			return false;
		}
		
	}
}
