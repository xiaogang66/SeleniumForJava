package lyg.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtil {
	public static String jdbcUrl;
	public static String driverClass;
	public static String username;
	public static String password;
	public static String initalPoolSize;
	public static String maxPoolSize;
	public static String maxIdleTime;
	public static Connection connection;
	public static Statement statement;
	
	static{
		//加载数据库配置
		try {
			PropertieUtil.loadPropertieFile("/conf/jdbc.properties");
			jdbcUrl=PropertieUtil.getValueByKey("jdbcUrl");
			driverClass=PropertieUtil.getValueByKey("driverClass");
			username=PropertieUtil.getValueByKey("username");
			password=PropertieUtil.getValueByKey("password");
			initalPoolSize=PropertieUtil.getValueByKey("initalPoolSize");
			maxPoolSize=PropertieUtil.getValueByKey("maxPoolSize");
			maxIdleTime=PropertieUtil.getValueByKey("maxIdleTime");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getConnection(){
		//获取数据库连接
		try {
			ComboPooledDataSource dataSource=new ComboPooledDataSource();
			dataSource.setJdbcUrl(jdbcUrl);//    设置连接字符串
			dataSource.setDriverClass(driverClass);//    设置驱动类
			dataSource.setUser(username);//    设置用户名
			dataSource.setPassword(password);//    设置密码
			dataSource.setInitialPoolSize(Integer.parseInt(initalPoolSize));//    设置初始化连接数
			dataSource.setMaxPoolSize(Integer.parseInt(maxPoolSize));//    设置最大连接数
			dataSource.setMaxIdleTime(Integer.parseInt(maxIdleTime));//    设置最大空闲时间
			connection=dataSource.getConnection();//    得到连接对象   
			statement=connection.createStatement(); 
		} catch (Exception e) {
			e.printStackTrace();
			connection=null;
		} 
	}
	
	public static ResultSet query(String sql){
		 try {
			 ResultSet resultSet=statement.executeQuery(sql);
			 return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static int update(String sql){
		 try {
				 int count=statement.executeUpdate(sql);
				 return count;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
	}
	
	public static void closeConnection() throws Exception{
		statement.close();
		connection.close();
	}
	
}