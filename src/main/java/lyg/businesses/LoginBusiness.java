package lyg.businesses;

import lyg.pages.LoginPage;

/**
 * @version:1.0
 * @Description: 登录测试业务
 * @author: XiaoGang 
 * @date: 2019年9月10日 下午7:54:24
 */
public class LoginBusiness extends BaseBusiness{
	
	LoginPage loginPage = new LoginPage();
	
	/**
	 * @throws Exception 
	 * @Description:登录业务操作
	 *
	 */
	public void testLogin() throws Exception{
		loginPage.inputUserName();
		loginPage.inputPassword();
		loginPage.clickLogin();
	}
}
