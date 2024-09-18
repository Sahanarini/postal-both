package com.postal.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postal.hibrepoimplementation.UserRepoImp;
import com.postal.model.User;
import com.postal.service.UserService;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepoImp repo;
	
	@Override
	public void addUser(User user) {
		repo.addUser(user);
		
	}

	@Override
	public void delUser(int id) {
		repo.delUser(id);
	}

	@Override
	public void updateUser(User user) {
		repo.updateUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return repo.getAllUsers();
	}

	@Override
	public User findById(int id) {
		return repo.findById(id);
		}

	@Override
	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public User Login(String email, String password) {
		User user = null;
		try {
			user = repo.Login(email, password);
		}catch(Exception e) {
			user = null;
		}
		return user;	
		}

}
