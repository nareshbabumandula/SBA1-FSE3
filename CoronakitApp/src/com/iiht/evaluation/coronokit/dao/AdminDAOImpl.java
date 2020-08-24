package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class AdminDAOImpl implements AdminDAO{

	private static final String GET_ALL_LNS_QRY = "SELECT Id, productName, cost, productDescription FROM ProductMaster";
	public static final String DEL_LN_QRY = "DELETE FROM productmaster WHERE Id=?";

	@Override
	public List<ProductMaster> listAllProducts() throws AdminException {
		List<ProductMaster> products = new ArrayList<>();
		
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_ALL_LNS_QRY);) {		

			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductMaster product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2)); 
				product.setCost(rs.getInt(3)); 
				product.setProductDescription(rs.getString(4)); 
				
				products.add(product);
			}
			
			if(products.isEmpty()) {
				products=null;
			}
		} catch (SQLException exp) {
			throw new AdminException("An error occured, Could not retrive the product details!");
		}
				
		return products;
	}

	@Override
	public String updateProduct() throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(int productId) throws AdminException {
		boolean isDeleted = false;

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_LN_QRY);) {

			pst.setInt(1, productId);

			int rowsCount = pst.executeUpdate();
			
			isDeleted= rowsCount>0 ;

		} catch (SQLException exp) {
			throw new AdminException("An error occured, Could not delete the product details!");
		}
		return isDeleted;
	}

}