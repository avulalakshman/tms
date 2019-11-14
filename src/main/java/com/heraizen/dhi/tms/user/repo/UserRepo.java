package com.heraizen.dhi.tms.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.heraizen.dhi.tms.domain.User;
import com.heraizen.dhi.tms.domain.UserStatus;

public interface UserRepo extends MongoRepository<User,String> {

	Optional<User> findByUsername(String userName);
	List<User> findByStatus(UserStatus userStatus);
	

}
