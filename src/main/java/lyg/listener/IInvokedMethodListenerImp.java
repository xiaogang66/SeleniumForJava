package lyg.listener;

import lyg.base.BaseManager;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class IInvokedMethodListenerImp extends BaseManager implements IInvokedMethodListener{
	//执行测试方法前执行
	public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
		String methodName=iTestResult.getName();
		if(methodName.startsWith("test")){
			//测试用例以test开头
			logger.debug("------执行用例："+methodName);
		}
	}

	//执行测试方法后执行
	public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
//		logger.debug("------执行用例："+iTestResult.getName());
	}
}
