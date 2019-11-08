package com.spaneos.dhi.tms.user.service;

import java.util.List;

import com.spaneos.dhi.tms.dto.UserDTO;

public class BulkLoadYAMUserService implements BulkUserLoaderService {

	public final String FILE_PATH;

	public BulkLoadYAMUserService(String FILE_PATH) {
		this.FILE_PATH = FILE_PATH;
	}

	@Override
	public List<UserDTO> loadUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
