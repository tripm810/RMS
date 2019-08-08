package com.studycase.springboot.rms.services;

import java.util.List;

import com.studycase.springboot.rms.entity.Role;


public interface RoleServices {
	List<Role> findAll();
}
