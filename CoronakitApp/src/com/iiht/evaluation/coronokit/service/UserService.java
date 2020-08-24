package com.iiht.evaluation.coronokit.service;

import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.User;

public interface UserService {
	
	void add(User user) throws AdminException;

}
