package com.spaneos.dhi.tms.user.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.spaneos.dhi.tms.domain.Role;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;

public class BulkLoadCSVUserService implements BulkUserLoaderService {
	public final String  FILE_PATH;

	public BulkLoadCSVUserService(String FILE_PATH) {
		this.FILE_PATH = FILE_PATH;
	}

	@Override
	public List<UserDTO> loadUsers() {
		try {
			List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
			return lines.stream().skip(1).map(line -> userDTO(line)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserDTO userDTO(String line) {
		String arr[] = line.split(",");
		UserDTO userDTO = UserDTO.builder().fullName(arr[0]).shortName(arr[1]).username(arr[2]).password(arr[3])
				.role(Role.CLIENT).status(UserStatus.REG_PENDING).build();

		return userDTO;
	}
}
