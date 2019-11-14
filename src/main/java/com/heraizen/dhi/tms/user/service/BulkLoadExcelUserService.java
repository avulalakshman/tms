package com.heraizen.dhi.tms.user.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heraizen.dhi.tms.domain.Role;
import com.heraizen.dhi.tms.domain.UserStatus;
import com.heraizen.dhi.tms.user.dto.UserDTO;

public class BulkLoadExcelUserService implements BulkUserLoaderService {
	public final Path path;

	private static final Logger LOG = LoggerFactory.getLogger(BulkLoadExcelUserService.class);

	protected BulkLoadExcelUserService(Path path) {
		this.path = path;
	}
	public static BulkUserLoaderService of(Path path) {
		return new BulkLoadExcelUserService(path);
	}

	@Override
	public List<UserDTO> loadUsers() {
		List<UserDTO> usersList = new ArrayList<>();
		try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(path.toString())))) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			LOG.info("Sheet with name:{} has max rows:{}", sheet.getSheetName(), lastRow);
			for (int i = 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				if (!isRowEmpty(row)) {
					usersList.add(getUserDto(sheet.getRow(i)));
				} else {
					LOG.info("Row doesn't contains data, it is empty!");
				}
			}
		} catch (IOException e) {
			LOG.error("Exception during loading users from file : {} ",e.getMessage());
		    LOG.debug("Stack Trace: {}",e);
		}
		return usersList;
	}

	private UserDTO getUserDto(Row row) {
		LOG.info("Processing row: {}", row.getRowNum());
		int c = 0;

		String fullName = row.getCell(c++).getStringCellValue();
		String shortName = row.getCell(c++).getStringCellValue();
		String username = row.getCell(c++).getStringCellValue();
		String password = row.getCell(c).getStringCellValue();
		return UserDTO.builder().fullName(fullName).shortName(shortName).username(username).password(password)
				.role(Role.CLIENT).status(UserStatus.REG_PENDING).build();

	}

	private boolean isRowEmpty(Row row) {
		return row == null || row.getLastCellNum() < 0;
	}

	
}
