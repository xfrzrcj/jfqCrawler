package com.mutliOrder.JiFenQiang.Crawler;

import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

public abstract class Step {
	//匹配的url
	private Pattern url;
	public Pattern getUrl() {
		return url;
	}
	public void setUrl(Pattern url) {
		this.url = url;
	}
	/**
	 * 执行步骤
	 * @param driver
	 */
	public abstract void doStep(WebDriver driver);
}
