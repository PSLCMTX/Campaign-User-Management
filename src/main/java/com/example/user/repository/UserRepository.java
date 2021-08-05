package com.example.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.user.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

	@Query("{name:?0}")
	User getUserByName(String name);

}
