package com.heraizen.dhi.tms.user.service;

import java.util.List;

import com.heraizen.dhi.tms.user.dto.UserDTO;

public interface BulkUserLoaderService {
		List<UserDTO> loadUsers();
}
