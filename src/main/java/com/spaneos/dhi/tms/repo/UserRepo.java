package com.spaneos.dhi.tms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spaneos.dhi.tms.domain.User;
import com.spaneos.dhi.tms.domain.UserStatus;

public interface UserRepo extends MongoRepository<User,String> {

	Optional<User> findByUsername(String userName);
	List<User> findByStatus(UserStatus userStatus);

}
