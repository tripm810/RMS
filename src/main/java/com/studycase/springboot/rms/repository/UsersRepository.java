package com.studycase.springboot.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studycase.springboot.rms.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	Users findByUserName(String userName);
}
