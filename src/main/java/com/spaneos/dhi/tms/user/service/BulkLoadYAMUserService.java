package com.spaneos.dhi.tms.user.service;

import java.nio.file.Path;
import java.util.List;

import com.spaneos.dhi.tms.dto.UserDTO;

public class BulkLoadYAMUserService implements BulkUserLoaderService {

	public final Path path;

	protected BulkLoadYAMUserService(Path path) {
		this.path = path;
	}
	public static BulkUserLoaderService of(Path path) {
		return new BulkLoadYAMUserService(path) ;
	}

	@Override
	public List<UserDTO> loadUsers() {
		return null;
	}

	

}
