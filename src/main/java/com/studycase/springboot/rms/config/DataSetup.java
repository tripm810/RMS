package com.studycase.springboot.rms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.studycase.springboot.rms.entity.Role;
import com.studycase.springboot.rms.repository.RoleRepository;
import com.studycase.springboot.rms.repository.UsersRepository;

public class DataSetup implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * @Autowired private UsersRepository usersRepository;
	 * 
	 * @Autowired private RoleRepository roleRepository;
	 * 
	 * 
	 * 
	 * @Override public void onApplicationEvent(ContextRefreshedEvent event) { if
	 * (roleRepository.findByName("ROLE_ADMIN") == null) { roleRepository.save(new
	 * Role("ROLE_ADMIN")); } }
	 */

}
