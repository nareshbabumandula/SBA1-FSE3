package com.iiht.evaluation.coronokit.service;

import com.iiht.evaluation.coronokit.dao.UserDAO;
import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;

public class UserServiceImpl implements UserService {

	UserDAO userDao;

	public UserServiceImpl() {
		userDao = new UserDAO();
	}

	@Override
	public void add(User user) throws AdminException {
		userDao.add(user);

	}

}
