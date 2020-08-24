package com.iiht.evaluation.coronokit.service;

import java.util.List;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public interface ProductService {
	
	void add(ProductMaster product) throws AdminException;
	
	void update(ProductMaster product) throws AdminException;
	
	void delete(String ids) throws AdminException;
	
	ProductMaster load(String id) throws AdminException;
	
	List<ProductMaster> list() throws AdminException;

}
