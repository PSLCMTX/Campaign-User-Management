package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.user.entity.DBSequence;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations operation;

	public int getSequenceNumber(String seqName) {
		Query query = new Query(Criteria.where("id").is(seqName));
		Update update = new Update().inc("seqNumber", 1);

		DBSequence counter = operation.findAndModify(query, update, options().returnNew(true).upsert(true),
				DBSequence.class);

		return !Objects.isNull(counter) ? counter.getSeqNumber() : 1;
	}
}
