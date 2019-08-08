package com.studycase.springboot.rms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycase.springboot.rms.entity.Role;
import com.studycase.springboot.rms.repository.RoleRepository;

@Service
public class RoleServicesImpl implements RoleServices {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

}
