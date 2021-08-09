package com.example.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Entity
public class User {

	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";

	@Id
	private int id;

	@NotNull(message = "{validation.name.empty}")
	private String name;
	
	@Size(min = 10, max = 10, message = "{validation.phone.notValid}")
	@NotNull(message = "{validation.phone.empty}")
	private String phone;

	@Email(message = "{validation.email.notValid}")
	@NotNull(message = "{validation.email.empty}")
	private String email;
	
	@NotNull(message = "{validation.age.empty}")
	private int age;
	
	@Column(nullable = true)
	private String address;

	@Enumerated(EnumType.STRING)
	private State state;
	
	

	/**
	 * @param id
	 * @param name
	 * @param phone
	 * @param email
	 * @param age
	 * @param address
	 * @param state
	 */
	public User(int id,String name,String phone,String email,int age, String address, State state) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
		this.address = address;
		this.state = state;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", age=" + age
				+ ", address=" + address + ", state=" + state + "]";
	}

}
