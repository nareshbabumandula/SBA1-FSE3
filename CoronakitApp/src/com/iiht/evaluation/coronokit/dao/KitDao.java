package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.Kit;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public KitDao() {

	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public void createKit(String[] ids) throws AdminException {
		List<ProductMaster> products = new ProductDAO().getSelectedProducts(ids);
		// Map<String, String> kitData = calculateKitQuantityAndCost(products);
		String uniqueID = UUID.randomUUID().toString();

		String createQuery = "INSERT INTO kit (id, noofitems, totalamount) values (?, ?, ?)";
		String insertKitProductsQuery = "INSERT INTO kitproducts (kitid, productid, productname, productcost) values(?, ?, ?, ?)";
		String insertKitQuery = "INSERT INTO kit (id, noofitems, totalamount) SELECT kitproducts COUNT(productid), SUM(productcost) where id = ?";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(insertKitProductsQuery);) {
			con.setAutoCommit(false);

			for (ProductMaster product : products) {
				pst.setString(1, uniqueID);
				pst.setInt(2, product.getId());
				pst.setInt(3, product.getCost());
				pst.setString(4, product.getProductDescription());
				pst.addBatch();
			}
			pst.executeBatch();
			con.setAutoCommit(true);

			// Insert into kit table
			pst.setString(1, uniqueID);
			pst.executeUpdate();

		} catch (SQLException exp) {
			throw new AdminException("An error occured, While adding the product!");
		}

	}

	public void createKit(String kitId) throws AdminException {
		String createKitQuery = "INSERT INTO kit (id, noofitems, totalamount) values (?, ?, ?)";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(createKitQuery);) {

			pst.setString(1, kitId);
			pst.setInt(2, 0);
			pst.setInt(3, 0);
			pst.executeUpdate();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While adding the product!");
		}
	}

	public void updateKit(String kitId) throws AdminException {
		String updateKitQuery = "UPDATE kit SET kit.noofitems = (select count(*) from kitproducts where kitproducts.kitid = kit.id), kit.totalamount = (select SUM(productcost) from kitproducts where kitproducts.kitid = kit.id) where KIT.ID = ?";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(updateKitQuery);) {
			pst.setString(1, kitId);
			pst.executeUpdate();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While adding the product!");
		}
	}

	public Kit showKit(String kitId) throws AdminException {
		String selectQuery = "select k.id, k.noofitems, k.totalamount, kp.productid, kp.productname, kp.productcost from kit k JOIN kitproducts kp ON (k.id = kp.kitid) WHERE kp.kitid = ?";
		List<ProductMaster> products = new ArrayList<ProductMaster>();
		ProductMaster productMaster = null;
		Kit kit = null;

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(selectQuery);) {
			pst.setString(1, kitId);
			try (ResultSet resultSet = pst.executeQuery()) {
				kit = new Kit();
				kit.setKitId(resultSet.getString(1));
				kit.setProductCount(resultSet.getInt(2));
				kit.setTotalAmount(resultSet.getInt(3));
				while (resultSet.next()) {
					// Product details
					productMaster = new ProductMaster();
					productMaster.setId(resultSet.getInt(4));
					productMaster.setProductName(resultSet.getString(5));
					productMaster.setCost(resultSet.getInt(6));
					productMaster.setProductDescription(resultSet.getString(7));
					products.add(productMaster);
				}
				kit.setProducts(products);
			}
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While fetching the products list!");
		}
		return kit;

	}

	public void addProductToKit(String kitId, String productId) throws AdminException {
		// Creating kit
		createKit(kitId);
		// Getting selected product to add it to the kit
		ProductMaster product = new ProductDAO().load(productId);
		// Query for adding to the kit products
		String insertKitProductsQuery = "INSERT INTO kitproducts (kitid, productid, productname, productcost) values(?, ?, ?, ?)";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(insertKitProductsQuery);) {

			pst.setString(1, kitId);
			pst.setInt(2, product.getId());
			pst.setString(3, product.getProductName());
			pst.setString(4, product.getProductDescription());

			pst.executeUpdate();

			// Update kit table as well
			updateKit(kitId);
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While adding the product!");
		}
	}

	public String placeOrder(String kitId, String address) throws AdminException {
		String orderKitQuery = "INSERT INTO order (id, kitid, status, address) values (?, ?, ?, ?)";
		String orderId = UUID.randomUUID().toString();
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(orderKitQuery);) {

			pst.setString(1, orderId);
			pst.setString(2, kitId);
			pst.setString(3, "in process");
			pst.setString(4, address);
			pst.executeUpdate();
		} catch (SQLException exp) {
			throw new AdminException("An error occured, While adding the product!");
		}
		return orderId;
	}
}