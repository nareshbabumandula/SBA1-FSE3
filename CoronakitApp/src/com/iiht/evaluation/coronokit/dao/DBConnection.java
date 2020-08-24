package com.iiht.evaluation.coronokit.dao;

import java.sql.*;

public class DBConnection {

	public static Connection getConnection() {
		try {

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coronakittestdb", "root", "root");
			return con;
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void releaseConnection(Connection conn) {
		try {
			if (!conn.isClosed())
				conn.close();
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}
}