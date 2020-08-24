package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;

public class LoginDAO {

	private static final String TABLE_NAME = "user";

	public User login(String userId, String password) throws AdminException {

		String loginQuery = "select id, password, type from " + TABLE_NAME + " where userid =" + userId
				+ " and password = " + password;
		User user = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(loginQuery);) {

			try (ResultSet resultSet = pst.executeQuery()) {
				user = new User();
				user.setId(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setType(resultSet.getString(3));
			}
		} catch (SQLException exp) {
			throw new AdminException("An error occured, User not exists!");
		}
		return user;

	}
}
