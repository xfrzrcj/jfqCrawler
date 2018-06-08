package com.mutliOrder.JiFenQiang.Crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public class Crawler {
	public static void main(String[] args) {
//		String sdf = "剩余12份";
//		System.out.println(sdf.substring(2, sdf.length()-1));
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-web-security","user-agent=Mozilla/5.0 (iPod; U; CPU iPhone OS 2_1 like Mac OS X; ja-jp) AppleWebKit/525.18.1 (KHTML, like Gecko) Version/3.1.1 Mobile/5F137 Safari/525.20");
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.eimoney.com/diamonds/mobile/index");
		
		LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
		System.out.println("The size of LocalStorage is:" + localStorage.size());
		localStorage.setItem("udid", "c6e3d4845c3c57f23de3d81334ca2c06a7e15835");
		System.out.println(localStorage.getItem("udid"));
		try {
			Thread.sleep(123324234);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
