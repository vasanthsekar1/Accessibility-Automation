package baseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import static utils.Util .*;

public class AccessibilityBaseClass {
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
		if(("#68b915").contains(resultColor)) {
			return true;
		}
		else{
			return false;
		}
	}

}
