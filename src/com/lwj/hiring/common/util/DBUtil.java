package com.lwj.hiring.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.lwj.hiring.common.Constants;



/**
 * 数据库操作
 * 
 * @author Lwj
 * @since 2016年9月6日
 */
public class DBUtil {
	
	private static final String DEFAULT_PROPERTIES_NAME = "jdbc";
	private DBUtil(){
		
	}
	
	/**
	 * 读取properties内容
	 * @param key
	 * @return
	 */
	public static String getDefaultBundle(String key){
		return getBundle(DEFAULT_PROPERTIES_NAME,key);
	}
	public static String getBundle(String propertiesName,String key){
		return ResourceBundle.getBundle(propertiesName).getString(key);
	}
	
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(Constants.DB_DRIVER);
			connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			System.err.println("数据库驱动加载失败！" + e.getMessage());

		} catch (SQLException e) {
			System.err.println("获取数据库连接失败！" + e.getMessage());

		}
		return connection;
	}

	/**
	 * 增删改操作
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		int row = 0;
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					preparedStatement.setObject(i + 1, params[i]);
				}
			}
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("操作数据库失败，" + e.getMessage());

		} finally {
			DBUtil.close(preparedStatement, connection);
		}
		return row;
	}

	public static int executeUpdate(String sql, List<Object> list) {
		return executeUpdate(sql, list.toArray());
	}

	/**
	 * 关闭数据库资源
	 */
	public static void close(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement preparedStatement, Connection connection) {
		close(preparedStatement);
		close(connection);
	}

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(preparedStatement, connection);
	}

	public static void setAutoCommit(Connection connection, boolean flag) {
		try {
			connection.setAutoCommit(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
