package com.mywebapp.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.User;

public class UserDao {
	Map<String, User> users = new HashMap<String, User>(){
		{
			put("John", new User("John", "Lennon", 600));
			put("Jimmy", new User("Jimmy", "Page", 1500));
			put("Neil", new User("Neil", "Young", 4000));
		}
	};
	
	public User findUser(String id) {
		return this.users.get(id);
	}
	
	public Collection<User> findAllList(){
		return this.users.values();
	}
	
	public void addUser(String key, User user) {
		this.users.put(key, user);
	}
	

}

