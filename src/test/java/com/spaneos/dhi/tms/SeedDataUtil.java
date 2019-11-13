/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaneos.dhi.tms;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.spaneos.dhi.tms.dto.UserDTO;
import com.spaneos.dhi.tms.repo.IssueAttachmentRepo;
import com.spaneos.dhi.tms.repo.UserRepo;
import com.spaneos.dhi.tms.user.service.FileType;

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
	public static final Function<Path, FileType> fileTypeDecider = (p) -> {
		String fileName = p.toString();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
		System.out.println(fileName + " and " + fileExt);
		switch (fileExt) {
		case "xlsx":
		case "xls":
			return FileType.EXCEL;
		case "yaml":
		case "yml":
			return FileType.YAML;
		case "json":
			return FileType.JSON;
		case "csv":
			return FileType.CSV;
		default:
			throw new IllegalArgumentException(String.format("File extenction type %s is not supported", fileExt));

		}
	};

}
