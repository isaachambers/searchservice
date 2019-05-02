package com.isaachambers.search.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(type = "customer", indexName = "customerindex", shards = 2)
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String documentId;
	private String firstName;
	private String secondName;
	private String country;
	private int age;

	public Customer() {

	}

	public Customer(String documentId, String firstName, String secondName, String country, int age) {
		super();
		this.documentId = documentId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.country = country;
		this.age = age;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
