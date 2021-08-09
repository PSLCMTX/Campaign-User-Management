package com.example.user.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequence")
public class DBSequence {

	@Id
	private String id;
	private int seqNumber;

	public DBSequence(String id, int seqNumber) {
		super();
		this.id = id;
		this.seqNumber = seqNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

}
