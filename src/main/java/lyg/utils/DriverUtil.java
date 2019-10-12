package lyg.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * @version:1.0
 * @Description: 动态获取浏览器驱动
 * @author: XiaoGang 
 * @date: 2019年9月10日 上午10:06:25
 */
public class DriverUtil {

	public static WebDriver getDriver(String driverType){
		try {
			switch (driverType){
				case "1" :
					Runtime.getRuntime().exec("tskill chrome");
					System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
					return new ChromeDriver();
				case "2":
					Runtime.getRuntime().exec("tskill firefox");
					System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
					return new FirefoxDriver();
				case "3":
					Runtime.getRuntime().exec("tskill iexplore");
					System.setProperty("webdriver.ie.driver","./driver/IEDriverServer.exe");
					return new InternetExplorerDriver();
				default:
					return null;
			}	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
