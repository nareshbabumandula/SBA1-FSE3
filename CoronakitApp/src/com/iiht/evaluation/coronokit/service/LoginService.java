package com.iiht.evaluation.coronokit.service;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;

public interface LoginService {
	
	User login(String userId, String password) throws AdminException;
}
