package lyg.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import lyg.utils.DriverUtil;
import lyg.utils.PropertieUtil;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;

public class BaseManager {
	
	public static WebDriver driver = null; 
	public static Logger logger = null; 
	
	static{
		try {
			//1、获取浏览器配置得到driver
			PropertieUtil.loadPropertieFile("/conf/env.properties");
			String driverType = PropertieUtil.getValueByKey("driver_type");
			driver=DriverUtil.getDriver(driverType);
			//2、浏览器初始化操作
			Options options = driver.manage();	
			options.window().maximize();
			options.timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			options.timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			options.timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
			//3、log4j日志配置
			//maven项目下获取配置文件资源方式1
//			String log4jPath=BaseManager.class.getClassLoader().getResource("conf/log4j.properties").getPath();
//			PropertyConfigurator.configure(log4jPath);
			
			//maven项目下获取配置文件资源方式2
			Properties log4jProperties=PropertieUtil.getProperties("/conf/log4j.properties");
			PropertyConfigurator.configure(log4jProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public BaseManager(){
		//获取log4j配置生成logger
		logger=Logger.getLogger(this.getClass());
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
	
	/**
	 * @Description:返回logger对象供失败截屏类中记录日志时调用
	 *  @return	logger日志对象
	 */
	public static Logger getLogger(){
		return logger;
	}
}
