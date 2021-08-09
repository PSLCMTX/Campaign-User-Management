package com.example.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	public SequenceGeneratorService service;

	/**
	 * Method is used to save user in the database
	 * @param user
	 * @return
	 */
	public User saveUser(User user) {
		user.setId(service.getSequenceNumber("user_sequence"));
		User u = repo.save(user);
		return u;
	}

	/**
	 * method is used to get user from the database based on ID.
	 * @param id
	 * @return
	 */
	
	public User getUserById(int id) {
		return repo.findById(id).get();
	}
	
	/**
	 * Method is used to get User from the database based on the Name.
	 * @param name
	 * @return
	 */

	public User getUserByName(String name) {
		return repo.getUserByName(name);
	}
	
	/**
	 * Method is used to delete user from database.
	 * @param id
	 */

	public void deleteUserById(int id) {
		repo.deleteById(id);
	}

	/**
	 * Method is used to update user.
	 * @param id
	 * @param user
	 * @return
	 */
	
	public Optional<Object> updateUser(int id, User user) {
		Optional<User> old = repo.findById(id);
		if (old.isPresent()) {
			old.get().setId(user.getId());
			old.get().setAddress(user.getAddress());
			old.get().setAge(user.getAge());
			old.get().setEmail(user.getEmail());
			old.get().setPhone(user.getPhone());
			old.get().setState(user.getState());
			repo.save(old.get());
			return Optional.of(old);
		}
		return Optional.empty();
	}

}
