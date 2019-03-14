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
 * @Description: ��װ�����ݿ�����ӹرյȲ���
 * @author: dengQingGui
 * 
 */
public class JdbcUtil {
	//�������ݿ���û���
	private  String username;
	//�������ݿ������
	private  String password;
	//�������ݿ�������Ϣ
	private  String driver;
	//����������ݿ�ĵ�ַ
	private  String url;
	// �������ݿ������
	private  Connection conn;
	// ����sql����ִ�ж���
	private  PreparedStatement pstmt;
	// �����ѯ���صĽ������
	private  ResultSet rs;
	
//	static {
//		// �������ݿ�������Ϣ��������ص����Ը�ֵ
//		loadConfig();
//	}
	public JdbcUtil() {
		loadConfig();
	}
	
	/**
	 * �������ݿ�������Ϣ��������ص����Ը�ֵ
	 */
	public  void loadConfig() {
		try {
			InputStream in = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
			//����һ��properties����(����)
			Properties p = new Properties();
			//����������
			p.load(in);
			//��ȡ��ز���ֵ
			username = p.getProperty("jdbc.username");
			password = p.getProperty("jdbc.password");
			driver = p.getProperty("jdbc.driver");
			url = p.getProperty("jdbc.url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return ���ݿ�����
	 */
	public Connection getConnection() {
		try {
			Class.forName(driver);//ע������
			try {
				conn = DriverManager.getConnection(url, username, password);//��ȡ����
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ͷ���Դ
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
	 * ִ�и��²���
	 * 
	 * @param sql
	 *            sql���
	 * @return ִ�н��
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
	 * ִ�в�ѯ����
	 * 
	 * @param sql
	 *            sql���
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
