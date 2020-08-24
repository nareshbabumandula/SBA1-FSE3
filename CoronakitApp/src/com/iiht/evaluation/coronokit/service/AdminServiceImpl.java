package com.iiht.evaluation.coronokit.service;

import java.util.List;

import com.iiht.evaluation.coronokit.dao.AdminDAOImpl;
import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class AdminServiceImpl implements AdminService{
	
    AdminDAOImpl adminDao;
	
	public AdminServiceImpl() {
		adminDao=new AdminDAOImpl();
	}

	@Override
	public List<ProductMaster> listAllProducts() throws AdminException {
		return adminDao.listAllProducts();
	}

	@Override
	public String updateProduct() throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(int productId) throws AdminException {
		return adminDao.deleteProduct(productId);
	}

}
