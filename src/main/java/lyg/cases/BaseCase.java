package lyg.cases;

import lyg.base.BaseManager;
import lyg.pages.BasePage;
import lyg.utils.PropertieUtil;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * @version:1.0
 * @Description: 测试用例基础类，打开主页和准备工作
 * @author: XiaoGang 
 * @date: 2019年9月10日 下午7:56:20
 */
public class BaseCase extends BaseManager{
	BasePage basePage=new BasePage();
	
	@BeforeSuite
	public void prepare() throws Exception{
		//测试之前打开浏览器
		String baseUrl = PropertieUtil.getValueByKey("base_url");
		basePage.get(baseUrl);
	}
	
	@BeforeClass
	public void logBeforeClass(){
		//进入类之前打印日志
		logger.debug("-----执行用例类："+this.getClass().getSimpleName());
	}
	
//	@BeforeMethod
//	public void logBeforeMethod(){
//		//进入方法之前打印日志
//		logger.debug("-----执行用例方法"+new Exception().getStackTrace()[0].getMethodName());
//	}
	
	
	@AfterClass
	public void logAfterClass(){
		//执行完测试类之后回到首页
		basePage.refresh();
	}
	
	@AfterSuite
	public void cleanup() throws Exception{
		//测试完毕退出并关闭浏览器
		basePage.close();
	}
	
}
