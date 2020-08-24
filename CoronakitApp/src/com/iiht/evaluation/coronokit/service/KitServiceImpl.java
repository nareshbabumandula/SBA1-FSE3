package com.iiht.evaluation.coronokit.service;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.Kit;
import com.iiht.evaluation.coronokit.model.OrderSummary;

public class KitServiceImpl implements KitService {
	
	KitDao kitDao;

	public KitServiceImpl() {
		kitDao = new KitDao();
	}

	@Override
	public void addProductToKit(String kitId, String productId) throws AdminException {
		kitDao.addProductToKit(kitId, productId);
		
	}

	@Override
	public Kit showKit(String kitId) throws AdminException {
		return kitDao.showKit(kitId);
	}

	@Override
	public String placeOrder(String kitId, String address) throws AdminException {
		return kitDao.placeOrder(kitId, address);
	}

}
