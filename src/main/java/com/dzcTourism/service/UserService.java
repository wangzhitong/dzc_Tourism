package com.dzcTourism.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dzcTourism.dao.UserRepository;
import com.dzcTourism.domain.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUser(String id) {
		userRepository.delete(id);
	}
	
	@Transactional
	public void updateUser(User user) {
		entityManager.merge(user);
	}
	
	
	public List<User> getUserAll(){
		return userRepository.findAll();
	}
	
	public User getUserById(String id) {
		return entityManager.find(User.class, id);
	}
	
	public boolean checkUsername(String userName) {
		User user = userRepository.getByUserName(userName);
		if(user != null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean login(String userName,String password) {
		User user = userRepository.login(userName, password);
		if(user != null) {
			return true;
		}
		return false;
	}
}
