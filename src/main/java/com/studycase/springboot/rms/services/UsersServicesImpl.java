package com.studycase.springboot.rms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studycase.springboot.rms.entity.Users;
import com.studycase.springboot.rms.repository.UsersRepository;

@Service
public class UsersServicesImpl implements UsersServices {


	private final UsersRepository usersRepository;

	public UsersServicesImpl(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	@Override
	public List<Users> findAll() {

		return usersRepository.findAll();
	}

	@Override
	public Users addUsers(Users users) {
		return usersRepository.save(users);

	}

	@Override
	public void deleteUsers(Long id) {
		usersRepository.deleteById(id);

	}

	@Override
	public Optional<Users> findbyId(Long id) {
		return usersRepository.findById(id);
	}



}
