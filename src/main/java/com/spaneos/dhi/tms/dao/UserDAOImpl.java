package com.spaneos.dhi.tms.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.spaneos.dhi.tms.domain.User;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.repo.UserRepo;
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User register(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> userById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> allUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> search(Predicate<Boolean> search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepo.findByUsername(userName);
	}

	@Override
	public List<User> registerUsers(List<User> users) {
		return userRepo.saveAll(users);
	}

	@Override
	public List<User> getUserByStatus(UserStatus userStatus) {
		return userRepo.findByStatus(userStatus);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepo.findAll(pageable);
	}

}
