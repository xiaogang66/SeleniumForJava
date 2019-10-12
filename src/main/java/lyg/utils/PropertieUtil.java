package lyg.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version:1.0
 * @Description: properties工具类
 * @author: XiaoGang 
 * @date: 2019年9月11日 下午5:58:44
 */
public class PropertieUtil {
	public static Properties pro = new Properties();
	
	public static void loadPropertieFile(String filePath) throws Exception{
		InputStream input = PropertieUtil.class.getResourceAsStream(filePath);
		pro.load(input);
	}
	
	public static String getValueByKey(String key) throws Exception{
		String result = pro.getProperty(key);
		return result;
	}
	
	/**
	 * @Description:根据相对类路径的资源文件路径，获取Properties
	 *  @param fileToClassPath
	 *  @return
	 *  @throws Exception
	 */
	public static Properties getProperties(String fileToClassPath) throws Exception{
		Properties propeties = new Properties();
		InputStream input = PropertieUtil.class.getResourceAsStream(fileToClassPath);
		propeties.load(input);
		return propeties;
	}

}
