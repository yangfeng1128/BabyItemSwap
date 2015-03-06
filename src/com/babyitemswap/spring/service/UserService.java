package com.babyitemswap.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.babyitemswap.spring.DOA.User;
import com.babyitemswap.spring.DOA.UsersDoa;

@Service("userService")
public class UserService {
	
	
	@Autowired
	private UsersDoa usersDoa;
	
	
	
	
	public void create(User user)
	{
		usersDoa.create(user);
	}

	public boolean exist(String username) {
		return usersDoa.exists(username);
		
	}
    @Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		
		return usersDoa.getAllUsers();
	}
    
    public User getUser(String username)
    {
    	return usersDoa.getUser(username);
    }


}
