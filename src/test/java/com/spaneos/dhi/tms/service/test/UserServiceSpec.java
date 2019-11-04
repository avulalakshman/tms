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

import com.spaneos.dhi.tms.SeedDataUtil;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;
import com.spaneos.dhi.tms.service.AlreadyUserExistsException;
import com.spaneos.dhi.tms.service.ExcelUserUtil;
import com.spaneos.dhi.tms.service.UserService;

/**
 *
 * @author Lakshaman
 */
@SpringBootTest
public class UserServiceSpec {
    @Autowired
    private SeedDataUtil seedDataUtil;
    @Autowired
    private ExcelUserUtil excelUserUtil;
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
        UserDTO savedUserDTO = userService.register(userDTO);
        assertThat(savedUserDTO.getId()).isNotEmpty();
        assertThat(userDTO.getUsername()).isSameAs(savedUserDTO.getUsername());
    }
    @Test
    @DisplayName("User registration with existing email")
    void registerUserWithExistingEmail() throws AlreadyUserExistsException {
    	 UserDTO userDTO = seedDataUtil.fetchUser();
         userService.register(userDTO);
         assertThrows(AlreadyUserExistsException.class,()->userService.register(userDTO));
         
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
    	List<UserDTO> users = excelUserUtil.fetchUsers(filePath);
    	List<UserDTO> savedUsers = userService.registerUsers(users);
    	assertThat(users).size().isEqualTo(savedUsers.size());
    }
    @Test
    @DisplayName("Registring multiple users on linux")
    @EnabledOnOs(OS.LINUX)
    void registerUsersLinux() {
    	String filePath = getExcelFilePath();
    	//String filePath = this.getClass().getResource("/users.csv").getPath().toString();
    	List<UserDTO> users = excelUserUtil.fetchUsers(filePath);
    	List<UserDTO> savedUsers = userService.registerUsers(users);
    	assertThat(users).size().isEqualTo(savedUsers.size());
    }
	
    @Test
    @DisplayName("Find users by the given status")
    void getUsersByStatus() {
    	String filePath = getExcelFilePath();
    	List<UserDTO> users = excelUserUtil.fetchUsers(filePath);
    	userService.registerUsers(users);
    	List<UserDTO> usersByStatus =  userService.getUserByStatus(UserStatus.APROVED);
    	assertThat(usersByStatus).size().isEqualTo(0);
    	usersByStatus =  userService.getUserByStatus(UserStatus.ENROLLED);
    	assertThat(usersByStatus).size().isEqualTo(3);
        usersByStatus =  userService.getUserByStatus(UserStatus.PENDING);
    	assertThat(usersByStatus).size().isEqualTo(0);
    	usersByStatus =  userService.getUserByStatus(UserStatus.DELETED);
    	assertThat(usersByStatus).size().isEqualTo(0);
    }
    
    
    private String getExcelFilePath() {
		return this.getClass()
    			.getResource("/users.xlsx")
    			.getPath().toString();
	}
    
}
