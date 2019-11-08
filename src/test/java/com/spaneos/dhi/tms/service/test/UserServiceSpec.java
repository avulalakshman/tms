/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaneos.dhi.tms.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.spaneos.dhi.tms.SeedDataUtil;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;
import com.spaneos.dhi.tms.service.AlreadyUserExistsException;
import com.spaneos.dhi.tms.service.UserService;
import com.spaneos.dhi.tms.user.service.FileType;
import com.spaneos.dhi.tms.user.service.BulkLoadUserServiceFactory;

@SpringBootTest
public class UserServiceSpec {
    @Autowired
    private SeedDataUtil seedDataUtil;
    @Autowired
    private BulkLoadUserServiceFactory loadUserFactory;
    @Autowired
    private UserService userService;
    
    @BeforeEach
    public void cleanData() {
    	seedDataUtil.deleteAll();
    }
    @Test
    @DisplayName("User registration with valid information")
    void registerUser() throws AlreadyUserExistsException{
        UserDTO userDTO = seedDataUtil.fetchUser();
        UserDTO savedUserDTO = userService.registerUser(userDTO);
        assertThat(savedUserDTO.getId()).isNotEmpty();
        assertThat(userDTO.getUsername()).isSameAs(savedUserDTO.getUsername());
    }
    @Test
    @DisplayName("User registration with existing email")
    void registerUserWithExistingEmail() throws AlreadyUserExistsException {
    	 UserDTO userDTO = seedDataUtil.fetchUser();
         userService.registerUser(userDTO);
         assertThrows(AlreadyUserExistsException.class,()->userService.registerUser(userDTO));
         
    }
    @Test
    @DisplayName("Registring multiple users on windows")
    @EnabledOnOs(OS.WINDOWS)
    void registerUsersWindows() {
    	String filePath = this.getClass().getResource("/users.xlsx")
    			.getPath()
    			.toString()
    			.substring(1);
    	
    	//String filePath = this.getClass().getResource("/users.csv").getPath().toString().substring(1);
    	List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.EXCEL,filePath).loadUsers();
    	List<UserDTO> savedUsers = userService.registerUsers(users);
    	assertThat(users).size().isEqualTo(savedUsers.size());
    }
    @Test
    @DisplayName("Registring multiple users on linux")
    @EnabledOnOs(OS.LINUX)
    void registerUsersLinux() {
    	String filePath = getExcelFilePath();
    	List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.CSV,filePath).loadUsers();
    	List<UserDTO> savedUsers = userService.registerUsers(users);
    	assertThat(users).size().isEqualTo(savedUsers.size());
    }
	
    @Test
    @DisplayName("Find users by the given status")
    void getUsersByStatus() {
    	String filePath = getExcelFilePath();
    	List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.EXCEL,filePath).loadUsers();
    	
    	userService.registerUsers(users);
    	List<UserDTO> usersByStatus =  userService.getUserByStatus(UserStatus.DEACTIVATED);
    	assertThat(usersByStatus).size().isEqualTo(0);
    	usersByStatus =  userService.getUserByStatus(UserStatus.REG_PENDING);
    	assertThat(usersByStatus).size().isEqualTo(3);
        usersByStatus =  userService.getUserByStatus(UserStatus.REGISTERED);
    	assertThat(usersByStatus).size().isEqualTo(0);
    }
    
    @Test
    @DisplayName("Find all users with pagination")
    void findAllWithPagination() {
    	String filePath = getExcelFilePath();
    	List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.EXCEL,filePath).loadUsers();
     	userService.registerUsers(users);
     	Pageable page = PageRequest.of(1, 2);
     	Page<UserDTO> retObject = userService.findAll(page);
     	
     	System.out.println(retObject.getTotalElements());
     	System.out.println(retObject.getTotalPages());
     	System.out.println(retObject.getContent().size());
     	
     	
    }
    
    private String getExcelFilePath() {
		return this.getClass()
    			.getResource("/users.xlsx")
    			.getPath().toString();
	}
    
}
