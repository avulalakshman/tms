package com.spaneos.dhi.tms.service.test;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spaneos.dhi.tms.dto.UserDTO;
import com.spaneos.dhi.tms.service.ExcelUserUtil;

@SpringBootTest
public class ExcelUserUtilTest {

		private ExcelUserUtil excelUserUtil;
		@Autowired
		public ExcelUserUtilTest(ExcelUserUtil excelUserUtil) {
			this.excelUserUtil = excelUserUtil;
		}
		
		@Test
		@EnabledOnOs(OS.WINDOWS)
		void testFetchUsersFromExcelOnWindows() {
			String filePath = this.getClass().getResource("/users.xlsx").getPath().toString().substring(1);
			List<UserDTO> list = excelUserUtil.fetchUsers(filePath);
			list.stream().forEach(System.out::println);
			assertThat(list).size().isEqualTo(3);
		}
		@Test
		@EnabledOnOs(OS.WINDOWS)
		void testFetchUsersFromCSVOnWindows() {
			String filePath = this.getClass().getResource("/users.csv").getPath().toString().substring(1);
			List<UserDTO> list = excelUserUtil.fetchUsers(filePath);
			list.stream().forEach(System.out::println);
			assertThat(list).size().isEqualTo(3);
		}
		
		@Test
		@EnabledOnOs(OS.LINUX)
		void testFetchUsersFromExcelOnLinux() {
			String filePath = this.getClass().getResource("/users.xlsx").getPath().toString();
			List<UserDTO> list = excelUserUtil.fetchUsers(filePath);
			assertThat(list).size().isEqualTo(3);
		}
		@Test
		@EnabledOnOs(OS.LINUX)
		void testFetchUsersFromCsvOnLinux() {
			String filePath = this.getClass().getResource("/users.csv").getPath().toString();
			List<UserDTO> list = excelUserUtil.fetchUsers(filePath);
			assertThat(list).size().isEqualTo(3);
		}
		
		
}
