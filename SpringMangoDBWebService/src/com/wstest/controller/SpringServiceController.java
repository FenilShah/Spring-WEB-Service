package com.wstest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wstest.model.User;



@RestController
@RequestMapping("/service/greeting")
public class SpringServiceController {
	
	@Autowired
	MongoOperations mongoOperations;
	
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody List<User> getGreeting(@PathVariable String name) {
		/*User user = new User();
		
		user.setUserName(name);
		user.setPassword("test");
		
		mongoOperations.save(user);
		*/
		
		List<User> users = (List<User>) mongoOperations.findAll(User.class);
		//return ("Hello "+name);
		return users;
		//return users;
	}
}
