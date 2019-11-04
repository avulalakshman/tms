package com.spaneos.dhi.tms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.domain.UserType;
import com.spaneos.dhi.tms.dto.UserDTO;

@Component
public class ExcelUserUtil {

	public List<UserDTO> fetchUsers(String filePath) {

		if (filePath.endsWith("csv")) {
			return loadFromCSV(filePath);
		} else if (filePath.endsWith("xlsx")) {
			return loadFromExcel(filePath);
		}
		return null;
	}

	private List<UserDTO> loadFromCSV(String filePath) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			return lines.stream().skip(1).map(line -> userDTO(line)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<UserDTO> loadFromExcel(String filePath) {
		List<UserDTO> usersList = new ArrayList<>();
		try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)))) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			for (int i = 1; i <= lastRow; i++) {
				usersList.add(getUserDto(sheet.getRow(i)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	private UserDTO getUserDto(Row row) {
		int c = 0;
		String fullName = row.getCell(c++).getStringCellValue();
		String shortName = row.getCell(c++).getStringCellValue();
		String username = row.getCell(c++).getStringCellValue();
		String password = row.getCell(c++).getStringCellValue();
		return UserDTO.builder()
				.fullName(fullName)
				.shortName(shortName)
				.username(username)
				.password(password)
				.userType(UserType.CUSTOMER)
				.status(UserStatus.ENROLLED)
				.build();
	}

	private UserDTO userDTO(String line) {
		String arr[] = line.split(",");
		UserDTO userDTO = UserDTO.builder().fullName(arr[0]).shortName(arr[1]).username(arr[2]).password(arr[3])
				.userType(UserType.CUSTOMER)
				.status(UserStatus.ENROLLED)
				.build();

		return userDTO;
	}

}
