package com.studycase.springboot.rms.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studycase.springboot.rms.entity.Role;
import com.studycase.springboot.rms.entity.Users;
import com.studycase.springboot.rms.repository.UsersRepository;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users users = usersRepository.findByUserName(username);
		if (users == null)
			throw new UsernameNotFoundException("User not found");

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : users.getRole()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return new org.springframework.security.core.userdetails.User(users.getUserName(), users.getPassword(),
				grantedAuthorities);
	}

}
