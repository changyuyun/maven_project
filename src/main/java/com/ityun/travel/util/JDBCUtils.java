package com.ityun.travel.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//jdbc工具类 druid
public class JDBCUtils {
	private static DataSource ds;

	static{
		Properties pro = new Properties();
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
		try {
			pro.load(is);
			ds = DruidDataSourceFactory.createDataSource(pro);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	/**
	 * 获取连接池
	 */
	public static DataSource getDataSource() {
		return ds;
	}

	/**
	 * 释放资源
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
