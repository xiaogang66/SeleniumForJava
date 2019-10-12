package lyg.listener;

import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;


/**
* @version:1.0
* @Description: 修改重跑机制用例数的统计方法
* @author: XiaoGang 
* @date: 2019年9月19日 下午1:58:34
*/
public class TestCountListener implements ITestListener {

	public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub	
	}

	public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub	
	}

	public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub	
	}

	public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
	// TODO Auto-generated method stub	
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Iterator<ITestResult> listOfFailedTests=context.getFailedTests().getAllResults().iterator();
		while(listOfFailedTests.hasNext()){
			ITestResult failedTest=listOfFailedTests.next();
			ITestNGMethod method=failedTest.getMethod();
			if(context.getFailedTests().getResults(method).size()>1){
				listOfFailedTests.remove();
			}else{
				if(context.getPassedTests().getResults(method).size()>0){
					listOfFailedTests.remove();
				}
			}	
		}
	}
}