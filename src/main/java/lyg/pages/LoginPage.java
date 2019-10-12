package lyg.pages;

import lyg.utils.PropertieUtil;

import org.openqa.selenium.By;

/**
 * @version:1.0
 * @Description:登录页面操作 
 * @author: XiaoGang 
 * @date: 2019年9月10日 下午7:54:37
 */
public class LoginPage extends BasePage{

	public void inputUserName() throws Exception {
		String username = PropertieUtil.getValueByKey("login_name");
		driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[2]/form/div[1]/div/div/input")).sendKeys(username);
	}
	
	public void inputPassword() throws Exception{
		String password = PropertieUtil.getValueByKey("login_password");
		driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[2]/form/div[2]/div/div/input")).sendKeys(password);
	}
	
	public void clickLogin(){
		driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div[2]/form/button")).click();
	}
	
}
