package lyg.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * @version:1.0
 * @Description:失败重跑次数调整 
 * @author: XiaoGang 
 * @date: 2019年9月19日 下午1:56:32
 */
public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount =1; // 失败测试重跑1次
	
	@Override
	public boolean retry(ITestResult result) {
		if (retryCount <maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}
}
