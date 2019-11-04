/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaneos.dhi.tms;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.spaneos.dhi.tms.dto.UserDTO;
import com.spaneos.dhi.tms.repo.UserRepo;

/**
 *
 * @author Lakshaman
 */
@Component
public class SeedDataUtil {
	
	@Autowired
	private UserRepo userRepo;
	
	private List<UserDTO> users;

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

	public UserDTO fetchUser() {
		return users.get(0);
	}

	public List<UserDTO> fetchUsers() {
		return users;
	}

	@PostConstruct
	void loadDataFromYAML() {
		
		Yaml yaml = new Yaml(new Constructor(UserDTOListMapper.class));
		UserDTOListMapper userDTOContainer = (UserDTOListMapper) yaml
					.load(this.getClass().getResourceAsStream("/users.yml"));
		this.users = userDTOContainer.getUsers();
	}
	
	public void deleteAll() {
		userRepo.deleteAll();
	}
	

}
