package lyg.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
 
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * @version:1.0
 * @Description: 失败重跑监听类
 * @author: XiaoGang 
 * @date: 2019年9月19日 下午1:57:00
 */
public class RetryListener implements IAnnotationTransformer {
	public void transform(ITestAnnotation annotation, Class testClass,
		Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if (retry == null) {
			annotation.setRetryAnalyzer(Retry.class);
		}
	}
}