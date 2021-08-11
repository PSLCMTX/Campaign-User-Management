package com.example.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.user.entity.State;
import com.example.user.entity.User;
import com.example.user.exceptionhandler.UserNotFoundException;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService service;
	
	
	@MockBean
	private UserRepository repo;
	
	private User user;
	
	private User userforId;


	@BeforeEach
	void setUp() throws Exception {
		user = new User(1,"Ajit","8975521116","ajit.k764@gmail.com",30,"Pune", State.ACTIVE);
		userforId = new User(1,"Ajit","8975521116","ajit.k764@gmail.com",30,"Pune", State.ACTIVE);
		
	}

	@Test
	void testSaveUser() throws Exception {
		User userInput = new User(1,"Ajit","8975521116","ajit.k764@gmail.com",30,"Pune", State.ACTIVE);
		Mockito.when(service.saveUser(userInput)).thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/user")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\r\n"
					+ "  \"address\": \"Pune\",\r\n"
					+ "  \"age\": 30,\r\n"
					+ "  \"email\": \"ajit.k764@gmail.com\",\r\n"
					+ "  \"id\": 3,\r\n"
					+ "  \"name\": \"MMMM\",\r\n"
					+ "  \"phone\": \"8975521116\",\r\n"
					+ "  \"state\": \"ACTIVE\"\r\n"
					+ "}")
			).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	void testGetUser() throws Exception {
		//fail("Not yet implemented");
		//Optional<User> user = Optional.of(new User(1,"Ajit","8975521116","ajit.k764@gmail.com",30,"Pune", State.ACTIVE));
		
		Mockito.when(service.updateUser(1,user)).thenReturn(Optional.of(user));
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(user));
		
		mockMvc.perform(get("/user/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

	@Test
	void testUpdateUser() throws Exception {
		
		//Mockito.when(service.getUserById(1)).thenReturn(Optional.of(user));
		//Mockito.when(repo.findById(1)).thenReturn(Optional.of(user));
		
		mockMvc.perform(put("/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "  \"address\": \"Pune\",\r\n"
						+ "  \"age\": 30,\r\n"
						+ "  \"email\": \"ajit.k764@gmail.com\",\r\n"
						+ "  \"id\": 3,\r\n"
						+ "  \"name\": \"MMMM\",\r\n"
						+ "  \"phone\": \"8975521116\",\r\n"
						+ "  \"state\": \"ACTIVE\"\r\n"
						+ "}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
       // .andExpect(content().contentType("application/json"));
	}

	@Test
	void testGetUserByName() throws Exception {
		mockMvc.perform(get("/user/name/Ajit")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

	@Test
	void testDeleteUser() throws Exception {
		service.deleteUserById(1);
		verify(service).deleteUserById(1);
		
		doNothing().when(service).deleteUserById(1);

		
		mockMvc.perform(delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

}
