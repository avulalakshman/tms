package com.spaneos.dhi.tms.service.test;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spaneos.dhi.tms.dto.UserDTO;
import com.spaneos.dhi.tms.user.service.FileType;
import com.spaneos.dhi.tms.user.service.BulkLoadUserServiceFactory;

@SpringBootTest
public class ExcelUserUtilTest {

		private BulkLoadUserServiceFactory loadUserFactory;
		@Autowired
		public ExcelUserUtilTest(BulkLoadUserServiceFactory loadUserFactory) {
			this.loadUserFactory = loadUserFactory;
		}
		
		@Test
		@EnabledOnOs(OS.WINDOWS)
		void testFetchUsersFromExcelOnWindows() {
			String filePath = this.getClass().getResource("/users.xlsx").getPath().toString().substring(1);
			List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.EXCEL,filePath).loadUsers();
	    	assertThat(users).size().isEqualTo(3);
		}
		@Test
		@EnabledOnOs(OS.WINDOWS)
		void testFetchUsersFromCSVOnWindows() {
			String filePath = this.getClass().getResource("/users.csv").getPath().toString().substring(1);
			List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.CSV,filePath).loadUsers();
	    	assertThat(users).size().isEqualTo(3);
		}
		
		@Test
		@EnabledOnOs(OS.LINUX)
		void testFetchUsersFromExcelOnLinux() {
			String filePath = this.getClass().getResource("/users.xlsx").getPath().toString();
			List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.EXCEL,filePath).loadUsers();
	    	
			assertThat(users).size().isEqualTo(3);
		}
		@Test
		@EnabledOnOs(OS.LINUX)
		void testFetchUsersFromCsvOnLinux() {
			String filePath = this.getClass().getResource("/users.csv").getPath().toString();
			List<UserDTO> users = loadUserFactory.getLoadUserService(()->FileType.EXCEL,filePath).loadUsers();
	    	
			assertThat(users).size().isEqualTo(3);
		}
		
		
}
