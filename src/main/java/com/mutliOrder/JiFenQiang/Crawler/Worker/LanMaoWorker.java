package com.mutliOrder.JiFenQiang.Crawler.Worker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mutliOrder.JiFenQiang.Crawler.ChromeCrawlerModel;
import com.mutliOrder.JiFenQiang.Crawler.Step;

public class LanMaoWorker extends ChromeCrawlerModel {
	public static final String taskTemp = "taskTemp";
	public static final String appDict = "appDict";

	@Override
	public void init() {
		getInnerMap().put(taskTemp, null);
		getInnerMap().put(appDict, new HashSet<String>());
		this.setStartUpStep(getStartStep());
		List<Step> stepList = new ArrayList<Step>();
		stepList.add(getTasksStep());
		stepList.add(getDetailStep());
		this.setStepList(stepList);
	}
	
	public Step getStartStep() {
		Step startStep = new Step() {

			@Override
			public void doStep(WebDriver driver) {
				//不断刷新直至得到正确的页面
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					driver.get("http://www.cattry.com/Tasknew/tasklist");
					if (driver.getCurrentUrl().contains("tasklist")) {
						break;
					}
				}
			}
		};
		return startStep;
	}
	
	/**
	 * 任务详情页面操作
	 * @return
	 */
	public Step getDetailStep() {
		final Pattern patternApp = Pattern.compile(".*appname:.*");
		final Pattern patternKey = Pattern.compile(".*keyword:\\'.*\\'");
		
		Step detailStep = new Step() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void doStep(WebDriver driver) {
				String[] lines = driver.getPageSource().split("\n");
				((Set<Object>)(getInnerMap().get(appDict))).add(getInnerMap().get(taskTemp));
				int len = lines.length;
				//从页面截取需要的字段
				for(int i = 0;i < len;i++) {
					Matcher patternAppMatch = patternApp.matcher(lines[i]);
					if(patternAppMatch.find()) {
						//TODO
						System.out.println(patternAppMatch.group());
					}
					Matcher patternKeyMatch = patternKey.matcher(lines[i]);
					if(patternKeyMatch.find()) {
						//TODO
						System.out.println(patternKeyMatch.group());
					}
				}
				//放弃任务并返回
				Actions backAction = new Actions(driver);
				backAction.click(driver.findElement(By.xpath("//*[@class='aui-pull-left ic-ico']"))).perform();
				Actions giveUpAction = new Actions(driver);
				giveUpAction.click(driver.findElements(By.className("aui-dialog-btn")).get(1)).perform();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		detailStep.setUrl(Pattern.compile(".*taskid.*"));
		return detailStep;
	}
	
	/**
	 * 任务清单页面操作
	 * @return
	 */
	public Step getTasksStep() {
		Step tasksStep = new Step() {

			@SuppressWarnings("unchecked")
			@Override
			public void doStep(WebDriver driver) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//避免页面跳转时间过长而错误进入此循环
				if(this.getUrl().matcher(driver.getCurrentUrl()).find()) {
					outer: while (true) {
						driver.navigate().refresh();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//任务框
						List<WebElement> tasks_frames = driver
								.findElements(By.xpath("//div[@class='v5-card v5-clearfix']"));
						//避免无任务时默认页面无法跳出
						if(tasks_frames.isEmpty()) {
							setAgain(false);
							break outer;
						}
						for (WebElement tasksFrame : tasks_frames) {
							String frameName = tasksFrame.findElement(By.className("v5-card-header")).findElement(By.tagName("span")).getAttribute("class");
							//只对进行中任务抓取
							if("tasking".equals(frameName)) {
								List<WebElement> clicks = driver.findElement(By.className("card-list-container")).findElements(By.xpath("//div[@class='card-item v5-clearfix']"));
								for(WebElement click:clicks) {
									//根据图片名对抓取的任务去重
									String taskName = click.findElement(By.tagName("img")).getAttribute("src");
									String[] words = click.getText().split("\n");
									String leave = words[1];
									//获取任务份数
									String num = leave.substring(2, leave.length()-1);
									//若任务数为0则表示抓完
									//TODO 还需要考虑全部遍历完的情况
									if("0".equals(num)) {
										setAgain(false);
										setRunning(false);
										break outer;
									}
									//只点未点过的任务
									if(!((Set<String>)getInnerMap().get(appDict)).contains(taskName)) {
										getInnerMap().put(taskTemp,taskName);
										String classTag = click.findElement(By.className("tag")).getText();
										//跳过回调任务和高额任务
										if((!"回调任务".equals(classTag))&&(!"高额任务".equals(classTag)) ) {
											Actions clickAction = new Actions(driver);
											clickAction.click(click).perform();
											break outer;
										}
									}
								}
							}
						}
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		tasksStep.setUrl(Pattern.compile("http://www.cattry.com/Tasknew/tasklist.*"));
		return tasksStep;
	}

	public static void main(String[] args) {
		new LanMaoWorker().run();
	}
}
