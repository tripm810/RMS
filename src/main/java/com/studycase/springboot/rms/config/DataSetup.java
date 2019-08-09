package com.studycase.springboot.rms.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import com.studycase.springboot.rms.entity.Role;
import com.studycase.springboot.rms.entity.Users;
import com.studycase.springboot.rms.repository.RoleRepository;
import com.studycase.springboot.rms.repository.UsersRepository;

@Component
public class DataSetup implements ApplicationListener<ContextRefreshedEvent> {

	private UsersRepository usersRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	
	
	public DataSetup(UsersRepository usersRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.usersRepository = usersRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (roleRepository.findByroleName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}
		if (roleRepository.findByroleName("ROLE_MEMBER") == null) {
			roleRepository.save(new Role("ROLE_MEMBER"));
		}
		
		if (usersRepository.findByUserName("admin") == null) {
            Users user = new Users();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            HashSet<Role> role = new HashSet<>();
            role.add(roleRepository.findByroleName("ROLE_ADMIN"));
            user.setRole(role);
            usersRepository.save(user);
        }
		
		if (usersRepository.findByUserName("user") == null) {
            Users user = new Users();
            user.setUserName("user");
            user.setPassword(passwordEncoder.encode("user"));
            HashSet<Role> role = new HashSet<>();
            role.add(roleRepository.findByroleName("ROLE_MEMBER"));
            user.setRole(role);
            usersRepository.save(user);
        }

	}
}
