package com.spaneos.dhi.tms.user.service;

import java.nio.file.Path;
import java.util.List;

import com.spaneos.dhi.tms.dto.UserDTO;

public class BulkLoadYAMUserService implements BulkUserLoaderService {

	public final Path path;

	public BulkLoadYAMUserService(Path path) {
		this.path = path;
	}

	@Override
	public List<UserDTO> loadUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public static BulkUserLoaderService of(Path path) {
		// TODO Auto-generated method stub
		return null;
	}

}
