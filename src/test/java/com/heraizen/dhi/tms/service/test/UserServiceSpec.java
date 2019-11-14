/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heraizen.dhi.tms.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.heraizen.dhi.tms.SeedDataUtil;
import com.heraizen.dhi.tms.domain.UserStatus;
import com.heraizen.dhi.tms.service.AlreadyUserExistsException;
import com.heraizen.dhi.tms.service.UserService;
import com.heraizen.dhi.tms.user.dto.UserDTO;
import com.heraizen.dhi.tms.user.service.BulkLoadUserServiceFactory;
import com.heraizen.dhi.tms.user.service.FileType;

@SpringBootTest
public class UserServiceSpec {

	/*
	 * @Autowired private SeedDataUtil seedDataUtil;
	 * 
	 * @Autowired private BulkLoadUserServiceFactory loadUserFactory;
	 * 
	 * @Autowired private UserService userService;
	 * 
	 * @BeforeEach public void cleanData() { seedDataUtil.deleteAll(); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("User registration with valid information") void registerUser()
	 * throws AlreadyUserExistsException { UserDTO userDTO =
	 * seedDataUtil.fetchUser(); UserDTO savedUserDTO =
	 * userService.registerUser(userDTO);
	 * assertThat(savedUserDTO.getId()).isNotEmpty();
	 * assertThat(userDTO.getUsername()).isSameAs(savedUserDTO.getUsername()); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("User registration with existing email") void
	 * registerUserWithExistingEmail() throws AlreadyUserExistsException { UserDTO
	 * userDTO = seedDataUtil.fetchUser(); userService.registerUser(userDTO);
	 * assertThrows(AlreadyUserExistsException.class, () ->
	 * userService.registerUser(userDTO));
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Registring multiple users") void registerUsers() { Path path =
	 * getExcelFilePath(); System.out.println("File Path:" + path.toString());
	 * List<UserDTO> users = loadUserFactory.getLoadUserService(fileTypeDecider,
	 * path).loadUsers(); List<UserDTO> savedUsers =
	 * userService.registerUsers(users);
	 * assertThat(users).size().isEqualTo(savedUsers.size()); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Find users by the given status") void getUsersByStatus() { Path
	 * path = getExcelFilePath(); List<UserDTO> users =
	 * loadUserFactory.getLoadUserService(fileTypeDecider, path).loadUsers();
	 * 
	 * userService.registerUsers(users); List<UserDTO> usersByStatus =
	 * userService.getUserByStatus(UserStatus.DEACTIVATED);
	 * assertThat(usersByStatus).size().isEqualTo(0); usersByStatus =
	 * userService.getUserByStatus(UserStatus.REG_PENDING);
	 * assertThat(usersByStatus).size().isEqualTo(3); usersByStatus =
	 * userService.getUserByStatus(UserStatus.REGISTERED);
	 * assertThat(usersByStatus).size().isEqualTo(0); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Find all users with pagination") void findAllWithPagination() {
	 * Path path = getExcelFilePath(); List<UserDTO> users =
	 * loadUserFactory.getLoadUserService(fileTypeDecider, path).loadUsers();
	 * userService.registerUsers(users); Pageable page = PageRequest.of(1, 2);
	 * Page<UserDTO> retObject = userService.findAll(page);
	 * assertThat(retObject.getTotalElements()).isEqualTo(3);
	 * assertThat(retObject.getTotalPages()).isEqualTo(2);
	 * assertThat(retObject.getContent()).size().isEqualTo(1);
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Find all users with pagination with sort") void
	 * findAllWithPaginationWithSort() { Path path = getExcelFilePath();
	 * List<UserDTO> users = loadUserFactory.getLoadUserService(fileTypeDecider,
	 * path).loadUsers(); userService.registerUsers(users); Pageable page =
	 * PageRequest.of(0, 10, Direction.ASC, "username"); Page<UserDTO> retObject =
	 * userService.findAll(page); List<String> pageUserNames =
	 * retObject.getContent().stream().map(UserDTO::getUsername)
	 * .collect(Collectors.toList()); List<String> storedUserNames =
	 * users.stream().map(UserDTO::getUsername).sorted().collect(Collectors.toList()
	 * );
	 * 
	 * assertThat(pageUserNames).containsSubsequence(storedUserNames);
	 * 
	 * }
	 */
	
	
}
