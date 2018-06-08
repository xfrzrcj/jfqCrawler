package com.mutliOrder.JiFenQiang.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverUtils {
	
	public static boolean elementExist(WebDriver driver,By element) {
		try{
			driver.findElement(element);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public static boolean elementExist(WebElement element,By by) {
		try{
			element.findElement(by);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
