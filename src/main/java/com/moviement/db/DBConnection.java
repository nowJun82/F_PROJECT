package com.moviement.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection connection;
	public static String DB_Name;
	public static String DB_USER;
	public static String DB_PASSWORD;
	public static int DB_PORT;

	public void connect() {
		String url;
		String user = DB_USER;
		String password = DB_PASSWORD;
		String driverName;
	}

	public int insert(String sql) {
		int id = -1;

		try {
			Statement stmt = connection.createStatement();
			stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
		}
		return id;
	}
}