package com.spaneos.dhi.tms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.spaneos.dhi.tms.domain.User;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.repo.UserRepo;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User register(User user) {
		Assert.notNull(user, "User object can't be null");
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user) {
		Assert.notNull(user, "User object can't be null");
		Assert.notNull(user.getId(), "Updated user id can't be null");
		return userRepo.save(user);
	}

	@Override
	public Optional<User> userById(String id) {
		Assert.notNull(id, "Search user id can't be null");
		return userRepo.findById(id);
	}

	@Override
	public boolean deleteUser(String id) {
		Assert.notNull(id, "Deleted user id can't be null");
		Optional<User> optUser = userRepo.findById(id);
		if (optUser.isPresent()) {
			userRepo.delete(optUser.get());
			return true;
		}
		return false;
	}

	@Override
	public List<User> allUsers() {
		return userRepo.findAll();
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
