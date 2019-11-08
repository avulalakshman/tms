package com.spaneos.dhi.tms.user.service;

import java.util.List;

import com.spaneos.dhi.tms.dto.UserDTO;

public interface BulkUserLoaderService {
		List<UserDTO> loadUsers();
}
