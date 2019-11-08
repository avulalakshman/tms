package com.spaneos.dhi.tms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spaneos.dhi.tms.dto.UserDTO;
import com.spaneos.dhi.tms.service.AlreadyUserExistsException;
import com.spaneos.dhi.tms.service.UserService;

@RestController
@RequestMapping("api/user/")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping
	public UserDTO registerUser(@RequestBody UserDTO userDTO) throws AlreadyUserExistsException {
		userDTO = userService.registerUser(userDTO);
		return userDTO;
	}
}
