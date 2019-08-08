package com.studycase.springboot.rms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studycase.springboot.rms.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	List<Role> findAll();
	

}
