package com.mutliOrder.JiFenQiang.Crawler.Worker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mutliOrder.JiFenQiang.Crawler.ChromeCrawlerModel;
import com.mutliOrder.JiFenQiang.Crawler.Step;
import com.mutliOrder.JiFenQiang.Utils.WebDriverUtils;

public class ShikeWorker extends ChromeCrawlerModel {
	public static final String taskTemp = "taskTemp";
	public static final String appDict = "appDict";

	@Override
	public void init() {
		getInnerMap().put(appDict, new HashSet<String>());
		this.setStartUpStep(getStartStep());
		List<Step> stepList = new ArrayList<Step>();
		stepList.add(getListStep());
		stepList.add(getDetailStep());
		this.setStepList(stepList);
	}

	private Step getStartStep() {
		Step startStep = new Step() {

			@Override
			public void doStep(WebDriver driver) {
				driver.get("http://appshike.com/orochi/FKLogin");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//点掉“开始赚钱“，刷新去掉领红包alert，点进入任务列表
				Actions action = new Actions(driver);
				action.click(driver.findElement(By.className("start-money")));
				action.pause(500).perform();
				driver.navigate().refresh();
				action.click(driver.findElement(By.xpath("//div[@class='try-play item']")));
				action.perform();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		return startStep;
	}
	
	private Step getListStep() {
		Step listStep = new Step() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void doStep(WebDriver driver) {
				String url = driver.getCurrentUrl();
				if(!getUrl().matcher(url.trim()).find()) {
					return;
				}
				while(WebDriverUtils.elementExist(driver, By.className("loading"))) {
					
					if("display: none;".equals(driver.findElement(By.className("loading")).getAttribute("style"))) {
						break;
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}				
				List<WebElement> list = driver.findElements(By.xpath("//dd[@data-v-176fa972]"));
				int count = 0;
				int len = list.size();
				for(WebElement ele:list) {
					String img = ele.findElement(By.className("l-img")).findElement(By.tagName("img")).getAttribute("src");
					if(!((Set<String>)getInnerMap().get(appDict)).contains(img)) {
						Actions click = new Actions(driver);
						click.click(ele).perform();
						getInnerMap().put(taskTemp, img);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						// TODO 获取页面份数数据
						//针对已下载的app的alert处理
						if(WebDriverUtils.elementExist(driver, By.className("btn_redbg"))) {
							((Set<String>)getInnerMap().get(appDict)).add(img);
							driver.navigate().refresh();
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						break;
					}
					count ++;
				}
				//抓完一次的标志
				if(len!=0&&count==len) {
					setAgain(false);
					setRunning(false);
				}
			}
		};
		listStep.setUrl(Pattern.compile("http://appshike.com/orochi/F[k|K]Try[P|p]lay$"));
		return listStep;
	}
	
	private Step getDetailStep() {
		//TODO 和fiddler交互截包，获取json
		Step backStep = new Step() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void doStep(WebDriver driver) {
				new Actions(driver).click(driver.findElement(By.xpath("//*[@class='iconfont icon-back back']"))).perform();
				new Actions(driver).click(driver.findElement(By.xpath("//input[@value='放弃任务']"))).perform();
				((Set<String>)getInnerMap().get(appDict)).add(getInnerMap().get(taskTemp).toString());
				System.out.println(getInnerMap().get(taskTemp).toString());
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		backStep.setUrl(Pattern.compile("http://appshike.com/orochi/FKTryPlay/FKDetails.*"));
		return backStep;
	}

	public static void main(String[] args) {
		new ShikeWorker().run();
	}
}
