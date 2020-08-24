package com.iiht.evaluation.coronokit.service;

import com.iiht.evaluation.coronokit.dao.LoginDAO;
import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;

public class LoginServiceImpl implements LoginService {

	@Override
	public User login(String userId, String password) throws AdminException {
		LoginDAO dao = new LoginDAO();//Use Factory pattern here to get respectiv DAO class
		return dao.login(userId, password);
	}

}
