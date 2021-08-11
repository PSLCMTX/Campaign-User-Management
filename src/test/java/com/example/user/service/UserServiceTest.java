package com.example.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
///;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.user.entity.State;
import com.example.user.entity.User;
import com.example.user.exceptionhandler.UserNotFoundException;
import com.example.user.repository.UserRepository;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository repo;

	@BeforeEach
	void setUp() throws Exception {

		Optional<User> user = Optional
				.of(new User(3	, "Ajit", "8975521116", "ajit.k764@gmail.com", 30, "Pune", State.ACTIVE));
		Mockito.when(repo.findById(3)).thenReturn(user);
	}

	@Test()
	void testGetUserById() throws UserNotFoundException {
		int uid = 3;
		User user = userService.getUserById(uid).get();
		assertEquals(3, user.getId());

	}
	
	
	@Test()
	void testSaveUser() {
		User userInput = new User(3	, "Ajit", "8975521116", "ajit.k764@gmail.com", 30, "Pune", State.ACTIVE);
		Mockito.when(repo.save(userInput)).thenReturn(userInput);
		User user = userService.saveUser(userInput);
		assertThat(user).isNotNull();
		assertEquals("Ajit", user.getName());

	}
	
	@Test()
	void testUpdateUser() {
		int userId=3;
		Optional<User> userInput = Optional.of(new User(3	, "Ajit", "8975521116", "ajit.k764@gmail.com", 30, "Pune", State.ACTIVE));
		User userInput2 = new User(3	, "Ajit", "8975521116", "ajit234.k764@gmail.com", 30, "Pune", State.ACTIVE);
		Mockito.when(repo.findById(3)).thenReturn(userInput);
		Optional<User> user = userService.updateUser(userId,userInput2);
		assertThat(user).isNotNull();
		assertEquals("ajit234.k764@gmail.com",user.get().getEmail());
	}
	
	@Test()
	void testDeleteUser() {
		User userInput = new User(3	, "Ajit", "8975521116", "ajit.k764@gmail.com", 30, "Pune", State.ACTIVE);
		userService.deleteUserById(1);
		verify(repo,times(1)).deleteById(1);

	}
	
}
