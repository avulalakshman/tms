package com.spaneos.dhi.tms.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.spaneos.dhi.tms.domain.User;
import com.spaneos.dhi.tms.domain.UserStatus;

public interface UserDAO {
	
	public User register(User user);
	public User updateUser(User user);
	public Optional<User> userById(String id);
	public boolean deleteUser(String id);
	public List<User> allUsers();
	public List<User> search(Predicate<Boolean> search);
	public Optional<User> findByUserName(String userName);
	public List<User> registerUsers(List<User> users);
	public List<User> getUserByStatus(UserStatus userStatus);
	
}
