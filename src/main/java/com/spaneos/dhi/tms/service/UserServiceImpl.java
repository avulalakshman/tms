package com.spaneos.dhi.tms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.spaneos.dhi.tms.dao.UserDAO;
import com.spaneos.dhi.tms.domain.User;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	private ModelMapper modelMapper;
	private UserDAO userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(ModelMapper modelMapper, UserDAO userDAO) {
		this.modelMapper = modelMapper;
		this.userDao = userDAO;
	}

	@Override
	public UserDTO register(UserDTO userDto) throws AlreadyUserExistsException {
		Assert.notNull(userDto, "User dto can't be null");
		Assert.notNull(userDto.getUsername(), "Username or email can't be empty or null");

		Optional<User> optionalUser = userDao.findByUserName(userDto.getUsername());

		if (optionalUser.isPresent()) {
			LOG.info("Already user registred with username/email : {} ", userDto.getUsername());
			throw new AlreadyUserExistsException(
					String.format("With given username: %s alreday registred", userDto.getUsername()));
		}

		User user = modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userDao.register(user);
		LOG.info("User is added successfully with id:{}", user.getId());
		userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserDTO> userById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserDTO> allUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<UserDTO> search(Predicate<Boolean> search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> registerUsers(List<UserDTO> usersDto) {
		// TODO check before adding user
		List<User> users = usersDto.stream()
				                   .map(dto -> modelMapper.map(dto, User.class))
				                   .collect(Collectors.toList());
		users = userDao.registerUsers(users);
		usersDto = users.stream()
				        .map(user -> modelMapper.map(user, UserDTO.class))
				        .collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public Map<UserStatus, Long> getUsersCountByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<UserStatus, List<UserDTO>> getUsersByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserByStatus(UserStatus userStatus) {
		Assert.notNull(userStatus, "User status can't be null");
		List<User> userList = userDao.getUserByStatus(userStatus);
		return userList.stream()
				.map(user->modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

}
