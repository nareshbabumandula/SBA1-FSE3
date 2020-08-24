package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;

public class UserDAO {

	private static final String TABLE_NAME = "user";

	public void add(User user) throws AdminException {

		String addQuery = "INSERT INTO user ('ID', 'password', 'type' values (?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(addQuery);) {

			pst.setInt(1, Integer.valueOf(user.getId()));
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getType());

			pst.executeUpdate();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While adding the new user!");
		}

	}

	public void update(User user) throws AdminException {

		String updateQuery = "UPDATE user SET password = ? type = ? WHERE ID = ?";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(updateQuery);) {

			// pst.setInt(1, product.getId());
			pst.setInt(1, Integer.valueOf(user.getId()));
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getType());

			pst.executeUpdate();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While updating the product!");
		}

	}

	public void delete(User user) {

	}

}
