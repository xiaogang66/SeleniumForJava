package lyg.cases;


import lyg.businesses.LoginBusiness;

import org.testng.annotations.Test;


/**
 * @version:1.0
 * @Description: 登录测试用例
 * @author: XiaoGang 
 * @date: 2019年9月10日 下午7:54:10
 */
public class LoginCase extends BaseCase{
	
	static LoginBusiness loginBusiness= new LoginBusiness();
	
	/**
	 * @throws Exception 
	 * @Description:登录测试用例
	 *
	 */
	@Test
	public void testLogin() throws Exception{
		loginBusiness.testLogin();
	}
	
	@Test
	public void testBaidu() throws Exception{
		loginBusiness.testLogin();
	}
	
	
	@Test
	public void testJd() throws Exception{
		loginBusiness.testLogin();
	}
}
