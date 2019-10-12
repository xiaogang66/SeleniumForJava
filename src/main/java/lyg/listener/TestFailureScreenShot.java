package lyg.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lyg.base.BaseManager;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestFailureScreenShot extends TestListenerAdapter{
	/**
	 * 失败后截屏
	 */
	public void onTestFailure(ITestResult tr) {
		WebDriver driver=BaseManager.getDriver();
		Logger logger=BaseManager.getLogger();
		String methodName=tr.getMethod().getMethodName();
		SimpleDateFormat format=new SimpleDateFormat("yyyMMdd_HHmmss");
		SimpleDateFormat dirNameFormat=new SimpleDateFormat("yyyMMdd");
		Date date=new Date();
		String picName=format.format(date)+".png";//图片文件名
		String dirName=dirNameFormat.format(date);//根据日期创建文件夹
		File screenShotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String fileName=methodName+"_"+picName;
			FileUtils.copyFile(screenShotFile,new File("./screenshot/"+dirName+"/"+fileName));
		} catch (IOException e) {
			logger.error(date+"----执行用例方法"+methodName+"失败，保存截屏失败");
			e.printStackTrace();
		}
	}
}
