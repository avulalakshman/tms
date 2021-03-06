package com.heraizen.dhi.tms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.heraizen.dhi.tms.domain.User;
import com.heraizen.dhi.tms.domain.UserStatus;

public interface UserDAO {
	
	public User register(User user);
	
	public User updateUser(User user);
	public Optional<User> userById(String id);
	public boolean deleteUser(String id);
	public List<User> allUsers();
	public Optional<User> findByUserName(String userName);
	public List<User> registerUsers(List<User> users);
	public List<User> getUserByStatus(UserStatus userStatus);
	public Page<User> findAll(Pageable pageable);
	
}
