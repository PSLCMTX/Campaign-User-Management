package com.example.user.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.user.entity.State;
import com.example.user.entity.User;

//@DataJpaTest
@SpringBootTest
@ExtendWith(SpringExtension.class)

class UserRepositoryTest {

	@MockBean
	private UserRepository repo;

	private User user;

	@BeforeEach
	void setUp() throws Exception {
		user = new User(1, "Ajit123", "8975521116", "ajit.k764@gmail.com", 30, "Pune", State.ACTIVE);

	}

	@Test
	void testGetUserByName() {
		Mockito.when(repo.getUserByName("Ajit")).thenReturn(user);
		assertEquals("Ajit", user.getName());
	}

}
