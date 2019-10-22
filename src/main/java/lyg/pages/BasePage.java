package lyg.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

import lyg.base.BaseManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends BaseManager{
	
	public JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
	public Actions actions=new Actions(driver);
	public static Robot robot;
	static {
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
	 * 打开网址
	 *  @param url
	 */
	public void get(String url){
		driver.get(url);
	}
	
	/**
	 * 按钮点击
	 *  @param element
	 */
	public void click(WebElement element){
		element.click();
	}
	
	/**
	 * 文本框输入
	 *  @param element
	 */
	public void sendKeys(WebElement element,String value){
		element.sendKeys(value);
	}
	
	/**
	 * 元素清空
	 *  @param element
	 */
	public void clear(WebElement element){
		element.clear();
	}
	
	/**
	 * 判断元素是否选中
	 *  @param element
	 */
	public void isSelected(WebElement element){
		element.isSelected();
	}
	
	/**
	 * 判断元素是否可用
	 *  @param element
	 */
	public void isEnabled(WebElement element){
		element.isEnabled();
	}
	
	/**
	 * 判断元素是否显示
	 *  @param element
	 */
	public void isDisplayed(WebElement element){
		element.isDisplayed();
	}
	
	/**
	 * 提交按钮类元素提交表单
	 *  @param element
	 */
	public void submit(WebElement element){
		element.submit();
	}
	
	/**
	 * 根据选项名称下拉选择
	 *  @param text
	 */
	public void selectByVisibleText(WebElement selectElement,String name){
		Select select = new Select(selectElement);
		select.selectByVisibleText(name);
	}
	
	/**
	 * 根据选项值下拉选择
	 *  @param text
	 */
	public void selectByValue(WebElement selectElement,String value){
		Select select = new Select(selectElement);
		select.selectByValue(value);
	}
	
	/**
	 * 根据下标索引下拉选择
	 *  @param text
	 */
	public void selectByIndex(WebElement selectElement,int index){
		Select select = new Select(selectElement);
		select.selectByIndex(index);
	}
	
	/**
	 * 关闭当前页面
	 *
	 */
	public void close() {
		driver.close();
	}

	/**
	 * 退出浏览器
	 *
	 */
	public void quit() {
		driver.quit();
	}

	/**
	 * 前进
	 *
	 */
	public void forward() {
		driver.navigate().forward();
	}

	/**
	 * 后退
	 *
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 获取当前url
	 *
	 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * 获取当前标题
	 *  @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 清除所有的cookies
	 *
	 */
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}
	
	/**
	 * 获取所有cookies
	 */
	public void getCookies(){
		driver.manage().getCookies();
	}
	
	/**
	 * 添加cookie
	 */
	public void addCookie(String cookieName,String cookieValue){
		Cookie cookie= new Cookie(cookieName, cookieValue);
		driver.manage().addCookie(cookie);
	}

	/**
	 * 等待时间（毫秒）
	 *  @param time
	 */
	public void Threadsleep(long milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 页面刷新
	 *  @throws NotFoundException
	 */
	public void refresh()throws NotFoundException{
		driver.navigate().refresh();
	}
	
	/**
	 * 判断页面是否刷新
	 * 
	 * @param trigger	判断页面
	 * @return
	 */
	public boolean waitPageRefresh(WebElement trigger) {
		int refreshTime = 0;
		boolean isRefresh = false;
		try {
			for (int i = 1; i < 60; i++) {
				refreshTime = i;
				trigger.getTagName();
				Thread.sleep(1000);	//每隔1秒获取一次标签
			}
		} catch (StaleElementReferenceException e) {
			isRefresh = true;
			logger.info("Page refresh time is:" + refreshTime + " seconds!");
			return isRefresh;
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Page didnt refresh in 60 seconds!");
		return isRefresh;
	}

	/**
	 * 根据id或name切换frame
	 *  @param frameId
	 */
	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);
	}

	/**
	 * 根据下标切换frame
	 *  @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * 回到默认frame
	 *
	 */
	public void defaultContent(){
		driver.switchTo().defaultContent();
	}

	/**
	 * 回到父级frame
	 *
	 */
	public void parentFrame(){
		driver.switchTo().parentFrame();
	}

	/**
	 * 跳转并获取弹框alert
	 *  
	 */
	private Alert switchToAlert() {
		return driver.switchTo().alert();
	}

	/**
	 * 点击alert的确定按钮
	 */
	public void acceptAlert() {
		switchToAlert().accept();
	}
	
	/**
	 * 点击取消和上面的关闭按钮
	 */
	public void dismissAlert() {
		switchToAlert().dismiss();
	}
	
	/**
	 * 判断弹窗是否存在
	 */
	public boolean isAlertExist() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception ex1) {
			return false;
		}
	}
	
	/**
	 * 处理一个潜在的弹窗:true表示点击确认，false表示点击取消
	 * 
	 * @param option
	 * @return
	 */
	public boolean dealPotentialAlert(boolean option) {
		boolean flag = false;
		Alert alert = driver.switchTo().alert();
		if (alert == null)
			logger.info("There is no alert appear!");
		else{
			if (option) {
				alert.accept();
				logger.info("Accept the alert: " + alert.getText());
			} else {
				alert.dismiss();
				logger.info("Dismiss the alert: " + alert.getText());
			}
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 适用于只弹出一个窗口的情况,直接切换到下一个窗口
	 *
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
			logger.info("title=" + window.getTitle() + ",url ="+ window.getCurrentUrl());
		}
	}

	/**
	 * 判断指定的窗口是否存在，最多遍历10次
	 *  @param windowTitle	指定窗口的title
	 *  @return
	 */
	public boolean windowIsExist(String windowTitle) {
		boolean flag = false;
		for (int a = 0; a < 9; a++) {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handler : windowHandles) {
				driver.switchTo().window(handler);
				String title = driver.getTitle();
				if (windowTitle.equals(title)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	

	/**
	 * 鼠标悬停事件
	 */
	public void moveToElement(WebElement element) {
		actions.moveToElement(element).perform();
	}

	/**
	 * 鼠标双击
	 */
	public void doubleClick(WebElement element) {
		actions.doubleClick(element).perform();
	}
	
	/**
	 * 鼠标右击
	 */
	public void rightClick(WebElement element) {
		actions.contextClick(element).perform();
	}
	
	/**
	 * 拖拽按钮至目标元素
	 *  @param element
	 */
	public void dragByElement(WebElement sourceElement,WebElement targetElement){
		actions.dragAndDrop(sourceElement, targetElement);
	}
	
	/**
	 * 拖拽按钮至指定坐标位置
	 *  @param sourceElement
	 *  @param xOffset
	 *  @param yOffset
	 */
	public void dragByOffset(WebElement sourceElement,int xOffset,int yOffset){
		actions.dragAndDropBy(sourceElement, xOffset, yOffset);
	}
	
	/**
	 * 利用js点击
	 * @param element	执行点击操作的页面对象
	 */
	public void clickByJs(WebElement element){
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * 利用js输入文本
	 * @param element	执行输入文本的页面对象	
	 * @param content	输入内容
	 */
	public void sendKeysByJs(WebElement element,String content){
		jsExecutor.executeScript("arguments[0].value=arguments[1];", element,content);
	}
	
	/**
	 * 给出指定信息的弹框
	 *  @param message
	 */
	public void alertByJs(String message){
		jsExecutor.executeScript("alert('"+message+"');");
	}
	
	/**
	 * 用js获取页面加载状态
	 *  
	 */
	public String getReadyStateByJs(){
		return jsExecutor.executeScript("return document.readyState").toString();
	}
	
	/**
	 * 用js得到页面title
	 *  
	 */
	public String getTitleByJs(){
		return (String)jsExecutor.executeScript("return document.title");
	}
	
	/**
	 * 用js获取标签属性
	 *  
	 */
	public void getAttrByJs(WebElement element,String attrName){
		jsExecutor.executeScript("arguments[0].getAttribute(arguments[1]);",element,attrName);		
	}
	
	
	/**
	 * 用js设置标签属性
	 *  
	 */
	public void setAttrByJs(WebElement element,String attrName,String attrValue){
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",element,attrName,attrValue);		
	}
	
	/**
	 * 用js移除标签属性
	 *  @param element
	 *  @param attrName
	 */
	public void removeAttrByJs(WebElement element,String attrName){
		jsExecutor.executeScript("arguments[0].removeAttribute(arguments[1]);",element,attrName);
	}
		
	/**
	 * 元素滚动到浏览器窗口的可视区域内
	 */
	public void scrollIntoView(WebElement element){
		jsExecutor.executeScript("arguments[0].scrollIntoView();",element); 	
		
	}
	
	/**
	 * 网页滚动条移动(x,y)
	 */
	public void scroll(int x,int y){
		jsExecutor.executeScript("scroll(arguments[0],arguments[1]);",x,y); 	
	}
		
	/**
	 * 窗口滚动到底部
	 */
	public void windowScrollToBottom(){
		jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight);"); 	
	}
	
	/**
	 * 窗口滚动到顶部
	 */
	public void windowScrollToTop(){
		jsExecutor.executeScript("window.scrollTo(0,0);"); 	
	}

	/**
	 * 元素纵向滚动
	 */
	public void elementScrollY(WebElement element,int y){
		jsExecutor.executeScript("arguments[0].scrollTop=arguments[1];",element,y); 	
	}
	
	/**
	 * 元素横向滚动
	 */
	public void elementScrollX(WebElement element,int x){
		jsExecutor.executeScript("arguments[0].scrollLeft=arguments[1];",element,x); 	
	}
	
		/**
	 * 键盘回车
	 */
	public void keyEnter(){
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	/**
	 * 键盘按下
	 */
	public void keyPress(int key){
		robot.keyPress(key);
	}
	
	/**
	 * 键盘弹出
	 */
	public void keyRelease(int key){
		robot.keyRelease(key);
	}
	
	/**
	 * 复制文本后粘贴并回车，常用于拷贝文件路径
	 */
	public void keyCopy(String content){
		StringSelection stringSelection = new StringSelection(content);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		robot.keyPress(KeyEvent.VK_CONTROL); 
		robot.keyPress(KeyEvent.VK_V); 
		robot.keyRelease(KeyEvent.VK_V); 
		robot.keyRelease(KeyEvent.VK_CONTROL); 
		robot.keyPress(KeyEvent.VK_ENTER); 
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
