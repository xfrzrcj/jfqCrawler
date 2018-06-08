package com.mutliOrder.JiFenQiang.Crawler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class ChromeCrawlerModel extends CrawlerModel {
	private ChromeOptions option;
	//操作步骤
	private List<Step> stepList;
	//初始化步骤
	private Step startUpStep;
	private boolean running = true;
	private boolean again = true;
	//step间参数传递
	private Map<String,Object> innerMap = new HashMap<String, Object>();

	public Map<String, Object> getInnerMap() {
		return innerMap;
	}

	public void setInnerMap(Map<String, Object> innerMap) {
		this.innerMap = innerMap;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isAgain() {
		return again;
	}

	public void setAgain(boolean again) {
		this.again = again;
	}

	public Step getStartUpStep() {
		return startUpStep;
	}

	public void setStartUpStep(Step startUpStep) {
		this.startUpStep = startUpStep;
	}

	public ChromeOptions getOption() {
		return option;
	}

	public void setOption(ChromeOptions option) {
		this.option = option;
	}

	public List<Step> getStepList() {
		return stepList;
	}

	public void setStepList(List<Step> stepList) {
		this.stepList = stepList;
	}
	/**
	 * 默认用Safari作为user-agent，禁用网站安全
	 * @return
	 */
	public ChromeDriver getDriver() {
		ChromeDriver driver = null;
		if (this.option != null) {
			driver = new ChromeDriver(this.option);
		} else {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--disable-web-security",
					"user-agent=Mozilla/5.0 (iPod; U; CPU iPhone OS 2_1 like Mac OS X; ja-jp) "
					+ "AppleWebKit/525.18.1 (KHTML, like Gecko) Version/3.1.1 Mobile/5F137 Safari/525.20");
			driver = new ChromeDriver(option);
		}
		return driver;
	}
	
	/**
	 * 流程：
	 * init()初始化参数->running控制是否继续循环->
	 * {
	 * 		打开chrome->打开初始页面->again控制是否循环->
	 * 		{
	 * 			遍历stepList->
	 * 			{
	 * 				当前页面url是否符合对应Step的url规则->符合则执行doStep方法
	 * 			}
	 * 		}
	 * 		->关闭chrome
	 * }
	 * 
	 */
	@Override
	public void run() {
		init();
		ChromeDriver driver = null;
		
			while (this.running) {
				try {
				driver = getDriver();
				this.startUpStep.doStep(driver);
				while (this.again) {
					for (Step step : stepList) {
						if(step.getUrl().matcher(driver.getCurrentUrl()).find()) {
							step.doStep(driver);
						}
					}
				}
				this.again = true;
				driver.quit();
				}catch(Exception e) {
					driver.quit();
					e.printStackTrace();
				}
			}
	}
}
