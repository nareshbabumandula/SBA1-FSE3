package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductDAO {

	private static final String TABLE_NAME = "productmaster";

	public void add(ProductMaster product) throws AdminException {

		String addQuery = "INSERT INTO productmaster (ID, productName, cost, productDescription) values (?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(addQuery);) {

			pst.setInt(1, product.getId());
			pst.setString(2, product.getProductName());
			pst.setInt(3, product.getCost());
			pst.setString(4, product.getProductDescription());

			pst.executeUpdate();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While adding the product!");
		}

	}

	public void update(ProductMaster product) throws AdminException {

		String updateQuery = "UPDATE productmaster SET productName = ?, cost = ?, productDescription = ? WHERE ID = ?";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(updateQuery);) {

			// pst.setInt(1, product.getId());
			pst.setString(1, product.getProductName());
			pst.setInt(2, product.getCost());
			pst.setString(3, product.getProductDescription());
			pst.setInt(4, product.getId());

			pst.executeUpdate();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While updating the product!");
		}

	}

	public void delete(String ids) throws AdminException {

		String deleteQuery = "DELETE FROM productmaster WHERE ID = ?";
		String[] idsArr = ids.split(",");

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(deleteQuery);) {
			// con.setAutoCommit(false);
			for (String productId : idsArr) {
				pst.setInt(1, Integer.valueOf(productId));
				pst.addBatch();
			}

			int[] rows = pst.executeBatch();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While deleting the product!");
		}

	}

	public List<ProductMaster> list() throws AdminException {

		String selectQuery = "select * from productmaster";
		List<ProductMaster> products = new ArrayList<ProductMaster>();
		ProductMaster productMaster = null;

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(selectQuery);) {

			try (ResultSet resultSet = pst.executeQuery()) {
				while (resultSet.next()) {
					productMaster = new ProductMaster();
					productMaster.setId(resultSet.getInt(1));
					productMaster.setProductName(resultSet.getString(2));
					productMaster.setCost(resultSet.getInt(3));
					productMaster.setProductDescription(resultSet.getString(4));
					products.add(productMaster);
				}
			}
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While fetching the products list!");
		}
		return products;

	}

	public ProductMaster load(String id) throws AdminException {

		String selectQuery = "select * from productmaster where ID = ?";
		ProductMaster productMaster = null;

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(selectQuery);) {

			pst.setInt(1, Integer.valueOf(id));
			try (ResultSet resultSet = pst.executeQuery()) {
				while (resultSet.next()) {
					productMaster = new ProductMaster();
					productMaster.setId(resultSet.getInt(1));
					productMaster.setProductName(resultSet.getString(2));
					productMaster.setCost(resultSet.getInt(3));
					productMaster.setProductDescription(resultSet.getString(4));
				}
			}
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While loading the product!");
		}
		return productMaster;

	}

	public List<ProductMaster> getSelectedProducts(String[] ids) throws AdminException {
		String selectQuery = "select * from productmaster where id = ?";
		List<ProductMaster> products = new ArrayList<ProductMaster>();
		ProductMaster productMaster = null;

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(selectQuery);) {
			for (String id : ids) {
				pst.setInt(1, Integer.valueOf(id));
				try (ResultSet resultSet = pst.executeQuery()) {
					while (resultSet.next()) {
						productMaster = new ProductMaster();
						productMaster.setId(resultSet.getInt(1));
						productMaster.setProductName(resultSet.getString(2));
						productMaster.setCost(resultSet.getInt(3));
						productMaster.setProductDescription(resultSet.getString(4));
						products.add(productMaster);
					}
				}

			}

		} catch (SQLException exp) {
			throw new AdminException("An error occured, While fetching the products list!");
		}
		return products;
	}

}
