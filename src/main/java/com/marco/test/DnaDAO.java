package com.marco.test;

import java.util.List;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class DnaDAO implements IDnaDAO {

	protected AmazonDynamoDBClient client = new AmazonDynamoDBClient(new DefaultAWSCredentialsProviderChain());
	protected DynamoDBMapper mapper;

	public DnaDAO() {
		client.setEndpoint("dynamodb.us-east-1.amazonaws.com");
		mapper = new DynamoDBMapper(client);
	}

	public void saveDnaTest(Dna dna) {
		this.mapper.save(dna);
	}

	public List<Dna> getStats() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		List<Dna> tests = mapper.scan(Dna.class, scanExpression);

		return tests;
	}

}
