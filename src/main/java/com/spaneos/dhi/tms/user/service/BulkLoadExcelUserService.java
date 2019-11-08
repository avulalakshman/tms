package com.spaneos.dhi.tms.user.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spaneos.dhi.tms.domain.Role;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;

public class BulkLoadExcelUserService implements BulkUserLoaderService {
	public final String  FILE_PATH;

	public BulkLoadExcelUserService(String FILE_PATH) {
		this.FILE_PATH = FILE_PATH;
	}

	@Override
	public List<UserDTO> loadUsers() {
		List<UserDTO> usersList = new ArrayList<>();
		try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(FILE_PATH)))) {
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
		return UserDTO.builder().fullName(fullName).shortName(shortName).username(username).password(password)
				.role(Role.CLIENT).status(UserStatus.REG_PENDING).build();
	}
}
