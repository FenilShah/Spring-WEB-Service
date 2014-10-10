package com.wstest.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.wstest.model.User;

public class RestClient {

	
	public static void main(String...args) throws JsonParseException, JsonMappingException, IOException{
		String plainCreds = "test:test";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/SpringMangoDBWebService/service/greeting/vipul";
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		String account = response.getBody();
		System.out.println(account);
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<User> users = objectMapper.readValue(account, TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));
		
		for(User user : users){
			System.out.println("Id: " + user.getId());
			System.out.println("Name: " + user.getUserName());
			System.out.println("Password: " + user.getPassword());
		}
	}
}
