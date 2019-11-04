package com.spaneos.dhi.tms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;

public interface UserService {
	
		public UserDTO register(UserDTO user)throws AlreadyUserExistsException;
		public List<UserDTO> registerUsers(List<UserDTO> users);
		public UserDTO updateUser(UserDTO user);
		public Optional<UserDTO> userById(String id);
		public boolean deleteUser(String id);
		public List<UserDTO> allUsers();
		public List<UserDTO> search(Predicate<Boolean> search);
		
		public Map<UserStatus,Long> getUsersCountByStatus();
		public Map<UserStatus,List<UserDTO>> getUsersByStatus();
		public List<UserDTO> getUserByStatus(UserStatus userStatus);
		
}
