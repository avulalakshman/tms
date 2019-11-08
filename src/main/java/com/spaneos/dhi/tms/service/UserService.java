package com.spaneos.dhi.tms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;

public interface UserService {
	
		public UserDTO registerUser(UserDTO user)throws AlreadyUserExistsException;
		public List<UserDTO> registerUsers(List<UserDTO> users);
		public UserDTO updateUser(UserDTO user);
		
		public boolean approveUser(String id);
		public Optional<UserDTO> findUserById(String id); 
		public boolean deactivateUser(String id);
		public List<UserDTO> getAllActiveUsers();
		public List<UserDTO> search(Predicate<Boolean> search); // search params
		public Page<UserDTO> findAll(Pageable pageable);
		
		public Map<UserStatus,Long> getUsersCountByStatus();
		public Map<UserStatus,List<UserDTO>> getUsersByStatus();
		public List<UserDTO> getUserByStatus(UserStatus userStatus);
		
		
}
