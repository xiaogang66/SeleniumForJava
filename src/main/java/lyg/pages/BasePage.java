package lyg.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Iterator;
import java.util.Set;

import lyg.base.BaseManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage extends BaseManager{
	
	public JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
	public Actions builder=new Actions(driver);
	public static Robot robot = null;
	
	static{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *
	 * @param expression	定位表达式，根据第一个字符确定定位方式
	 *	    ".xxx"	根据className获取
	 * 		"<xxx"	根据css表达式获取
	 *	    "#xxx"	根据id获取
	 *	    "_xxx"	根据linkText获取
	 *	    "-xxx"	根据模糊linkText获取
	 *	    "@xxx"	根据name属性获取
	 *	    ">xxx"	根据xpath获取
	 *	    "xxx"	根据标签名获取
	 *	@return	返回定位到的元素
	 */
	public WebElement findElement(String expression){
		try {
			String flag = expression.substring(0, 1);
			String realExpre = expression.substring(1, expression.length());
			switch(flag){
				case ".":
					return driver.findElement(By.className(realExpre));
				case "<":
					return driver.findElement(By.cssSelector(realExpre));
				case "#":
					return driver.findElement(By.id(realExpre));
				case "_":
					return driver.findElement(By.linkText(realExpre));
				case "-":
					return driver.findElement(By.partialLinkText(realExpre));
				case "@":
					return driver.findElement(By.name(realExpre));
				case ">":
					return driver.findElement(By.xpath(realExpre));
				default:
					return driver.findElement(By.tagName(realExpre));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据表达式查找元素
	 * @param baseElement	基础页面对象
	 * @param expression	表达式
	 * @return				查找到的页面对象
	 * @throws Exception		
	 */
	public WebElement findElement(WebElement baseElement,String expression) throws Exception{
		try {
			String flag = expression.substring(0, 1);
			String realExpre = expression.substring(1, expression.length());
			switch(flag){
				case ".":
					return baseElement.findElement(By.className(realExpre));
				case "<":
					return baseElement.findElement(By.cssSelector(realExpre));
				case "#":
					return baseElement.findElement(By.id(realExpre));
				case "_":
					return baseElement.findElement(By.linkText(realExpre));
				case "-":
					return baseElement.findElement(By.partialLinkText(realExpre));
				case "@":
					return baseElement.findElement(By.name(realExpre));
				case ">":
					return baseElement.findElement(By.xpath(realExpre));
				default:
					return baseElement.findElement(By.tagName(realExpre));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 执行元素点击的js脚本
	 * @param element	执行点击操作的页面对象
	 */
	public void click(WebElement element){
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * 执行元素输入文本的js脚本
	 * @param element	执行输入文本的页面对象	
	 * @param content	输入内容
	 */
	public void sendKeys(WebElement element,String content){
		jsExecutor.executeScript("arguments[0].value=arguments[1];", element,content);
	}
	
	
	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public void forward() {
		driver.navigate().forward();
	}

	public void back() {
		driver.navigate().back();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 清除所有的cookies
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}
	/**
	 *等待时间
	 */
	public void Threadsleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 页面刷新
	 */
	public static void refresh()throws NotFoundException{
		driver.navigate().refresh();
	}


	/**
	 * 改进版的切换指定的窗口,适用于只弹出一个窗口的情况,不需要传入任何参数,直接切换到下一个窗口
	 */
	public void switchToWindow() {
		// 得到当前窗口的句柄
		String currentWindow = driver.getWindowHandle();
		// 得到所有窗口的句柄
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			String handle = it.next();
			if (currentWindow.equals(handle))
				continue;
			WebDriver window = driver.switchTo().window(handle);
			logger.info("title,url = " + window.getTitle() + ","
					+ window.getCurrentUrl());
		}
	}

	/**
	 * 判断指定的窗口存在--遍历最多10次，找到为止，适用于多个弹窗的情况，传入指定窗口的title
	 * 
	 * @param windowTitle
	 * @return
	 */
	public boolean switchToWindow(String windowTitle) {
		boolean flag = false;
		for (int a = 0; a < 9; a++) {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handler : windowHandles) {
				driver.switchTo().window(handler);
				String title = driver.getTitle();
				if (windowTitle.equals(title)) {
					a = 10;
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断指定的窗口存在--适用于多个弹窗的情况，传入指定窗口的title(尚未测试)
	 * 
	 * @param windowTitle
	 * @return
	 */
	public boolean switchToWindow2(String windowTitle) {
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String handler : handles) {
				if (handler.equals(currentHandle))
					continue;
				else {
					driver.switchTo().window(handler);
					if (driver.getTitle().equals(windowTitle)) {
						flag = true;
						logger.info("Switch to window: " + windowTitle
								+ " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			logger.info("Window: " + windowTitle + " cound not found!",
					e.fillInStackTrace());
			flag = false;
		}
		return flag;
	}

	/**
	 * 切换到对于的frame，传入参数为frame在dom中的id
	 */
	public void switchToFrame(String frameId) {
		driver.switchTo().frame(frameId);
	}

	/**
	 * 切换到对于的frame，传入参数为frame在dom中的index，从0开始
	 */
	public void switchToFrame(int frameId) {
		driver.switchTo().frame(frameId);
	}
	/**
	 * frame切换
	 */
	public void defaultContent(){
		driver.switchTo().defaultContent();
	}
	/**
	 * 切换到父一层
	 */
	public void parentFrame(){
		driver.switchTo().parentFrame();
	}

	// 定位到所对应的Alert
	private Alert switchToAlert() {
		return driver.switchTo().alert();
	}

	// 点击alert的确定按钮
	public void acceptAlert() {
		switchToAlert().accept();
	}
	/*
	 * 模拟js点击
	 */
	public void Js_click(WebElement e){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",e);

	}

	// 点击取消和上面的关闭按钮
	public void dismissAlert() {
		switchToAlert().dismiss();
	}

	/**
	 * 模拟鼠标悬停事件
	 */
	public void moveToElement(By locator) {
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(locator)).perform();
	}

	/**
	 * 判断弹窗存在
	 * added by harry 2013-01-11
	 */
	public boolean isAlertExist() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex1) {
			return false;
		} catch (NoSuchWindowException ex2) {
			return false;
		}
	}

	/**
	 * 判断页面是否刷新
	 * added by harry 2013-01-29
	 * 
	 * @param trigger
	 * @return
	 */
	public boolean waitPageRefresh(WebElement trigger) {
		int refreshTime = 0;
		boolean isRefresh = false;
		try {
			for (int i = 1; i < 60; i++) {
				refreshTime = i;
				trigger.getTagName();
				Thread.sleep(1000);
			}
		} catch (StaleElementReferenceException e) {
			isRefresh = true;
			logger.info("Page refresh time is:" + refreshTime + " seconds!");
			return isRefresh;
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Page didnt refresh in 60 seconds!");
		return isRefresh;
	}

	/**
	 * 处理一个潜在的弹窗
	 * added by harry 2013-01-29
	 * 
	 * @param driver
	 * @param option
	 *            :true(click ok);false(click cancel)
	 * @return
	 */
	public boolean dealPotentialAlert(boolean option) {
		boolean flag = false;
		try {
			Alert alert = driver.switchTo().alert();
			if (null == alert)
				throw new NoAlertPresentException();
			try {
				if (option) {
					alert.accept();
					logger.info("Accept the alert: " + alert.getText());
				} else {
					alert.dismiss();
					logger.info("Dismiss the alert: " + alert.getText());
				}
				flag = true;
			} catch (WebDriverException ex) {
				if (ex.getMessage().startsWith("Could not find"))
					logger.info("There is no alert appear!");
				else
					throw ex;
			}
		} catch (NoAlertPresentException e) {
			logger.info("There is no alert appear!");
		}
		return flag;
	}
}
