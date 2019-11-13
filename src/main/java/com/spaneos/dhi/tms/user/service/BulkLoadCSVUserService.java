package com.spaneos.dhi.tms.user.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spaneos.dhi.tms.domain.Role;
import com.spaneos.dhi.tms.domain.UserStatus;
import com.spaneos.dhi.tms.dto.UserDTO;

public class BulkLoadCSVUserService implements BulkUserLoaderService {
	
	private static final Logger LOG = LoggerFactory.getLogger(BulkLoadCSVUserService.class);	
	public final Path  path;

	protected BulkLoadCSVUserService(Path path) {
		this.path = path;
	}
	public static  BulkLoadCSVUserService of(Path path) {
		return new BulkLoadCSVUserService(path);
	}

	@Override
	public List<UserDTO> loadUsers() {
		try {
			List<String> lines = Files.readAllLines(this.path);
			return lines.stream().skip(1).map(this::toUserDTO).collect(Collectors.toList());
		} catch (IOException e) {
			LOG.error("Exception during loading users from file : {} ",e.getMessage());
		    LOG.debug("Stack Trace: {}",e);
		}
		return Collections.emptyList();
	}

	private UserDTO toUserDTO(String line) {
		String[] arr= line.split(",");
		return UserDTO.builder()
				.fullName(arr[0])
				.shortName(arr[1])
				.username(arr[2])
				.password(arr[3])
				.role(Role.CLIENT)
				.status(UserStatus.REG_PENDING)
				.build();

	}
}
