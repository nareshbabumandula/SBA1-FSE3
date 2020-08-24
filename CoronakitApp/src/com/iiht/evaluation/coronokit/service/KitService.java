package com.iiht.evaluation.coronokit.service;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.Kit;

public interface KitService {
	
	void addProductToKit(String kitId, String productId) throws AdminException;
	
	Kit showKit(String kitId) throws AdminException;
	
	String placeOrder(String kitId, String address) throws AdminException;

}
