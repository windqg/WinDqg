package com.deng.jdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName: JdbcUtil.java
 * @Description: 封装了数据库的连接关闭等操作
 * @author: dengQingGui
 * 
 */
public class JdbcUtil {
	//定义数据库的用户名
	private  String username;
	//定义数据库的密码
	private  String password;
	//定义数据库驱动信息
	private  String driver;
	//定义访问数据库的地址
	private  String url;
	// 定义数据库的链接
	private  Connection conn;
	// 定义sql语句的执行对象
	private  PreparedStatement pstmt;
	// 定义查询返回的结果集合
	private  ResultSet rs;
	
//	static {
//		// 加载数据库配置信息，并给相关的属性赋值
//		loadConfig();
//	}
	public JdbcUtil() {
		loadConfig();
	}
	
	/**
	 * 加载数据库配置信息，并给相关的属性赋值
	 */
	public  void loadConfig() {
		try {
			InputStream in = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
			//创建一个properties对象(集合)
			Properties p = new Properties();
			//加载输入流
			p.load(in);
			//获取相关参数值
			username = p.getProperty("jdbc.username");
			password = p.getProperty("jdbc.password");
			driver = p.getProperty("jdbc.driver");
			url = p.getProperty("jdbc.url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * 
	 * @return 数据库连接
	 */
	public Connection getConnection() {
		try {
			Class.forName(driver);//注册驱动
			try {
				conn = DriverManager.getConnection(url, username, password);//获取连接
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 释放资源
	 */
	public void release() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 执行更新操作
	 * 
	 * @param sql
	 *            sql语句
	 * @return 执行结果
	 * @throws SQLException
	 */
	public  boolean executeUpdate(String sql) {
		if (conn == null) {
			getConnection();
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 执行查询操作
	 * 
	 * @param sql
	 *            sql语句
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql) {
		ResultSet rs;
		try {
			if (conn == null) {
				getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			try {
				rs = pstmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return rs;
	}
}
