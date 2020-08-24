package com.iiht.evaluation.coronokit.dao;

import java.util.List;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public interface AdminDAO {
	
	List<ProductMaster> listAllProducts() throws AdminException ;

	String updateProduct() throws AdminException;

	boolean deleteProduct(int productId) throws AdminException;
		
}
