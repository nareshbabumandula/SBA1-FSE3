package com.iiht.evaluation.coronokit.service;

import java.util.List;

import com.iiht.evaluation.coronokit.dao.ProductDAO;
import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductServiceImpl implements ProductService {
	
    ProductDAO productDao;
	
	public ProductServiceImpl() {
		productDao =new ProductDAO();
	}

	@Override
	public void add(ProductMaster product) throws AdminException {
		productDao.add(product);

	}

	@Override
	public void update(ProductMaster product) throws AdminException {
		productDao.update(product);

	}

	@Override
	public void delete(String ids) throws AdminException {
		productDao.delete(ids);

	}

	@Override
	public List<ProductMaster> list() throws AdminException {
		return productDao.list();
		}

	@Override
	public ProductMaster load(String id) throws AdminException {
		return productDao.load(id);
	}

}
