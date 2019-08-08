package com.studycase.springboot.rms.services;

import java.util.List;
import java.util.Optional;

import com.studycase.springboot.rms.entity.Users;

public interface UsersServices {
	
	List<Users> findAll();

	Users addUsers(Users users);
	
	void deleteUsers(Long id);
	
	Optional<Users> findbyId(Long id);

}
