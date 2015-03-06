package com.babyitemswap.spring.test.tests;


import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.babyitemswap.spring.DOA.User;
import com.babyitemswap.spring.DOA.UsersDoa;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/babyitemswap/spring/config/dao-context.xml",
		"classpath:com/babyitemswap/spring/config/security-context.xml",
		"classpath:com/babyitemswap/spring/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDoaTest {
	@Autowired
	private UsersDoa usersDoa;
	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("LeeShyu111","Feng Yang","fengfeng", true,"ROLE_ADMIN","feng1@gmail.com");
	private User user2 = new User("fenfeng2","Fen2 Yan","feng1234", true,"ROLE_USER","feng2@gmail.com");
	private User user3 = new User("fengfeng3","Feng3 Yang","fengfeng2", true,"ROLE_USER","feng3@gmail.com");
	private User user4 = new User("fengfeng4","Feng4 Yang","fengfeng3", true,"ROLE_USER","feng4@gmail.com");
	
	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testExists(){
		usersDoa.create(user1);
		usersDoa.create(user2);
		usersDoa.create(user3);
		assertTrue("User should exist.", usersDoa.exists(user2.getUsername()));
		assertFalse("User should not exist.", usersDoa.exists("lioninesxs"));
	
	}
	
	@Test
	public void testCreateRetrieve()
	{
		usersDoa.create(user1);
		List<User> userList = usersDoa.getAllUsers();
		assertEquals("One user should be created and retrieved.", 1, userList.size());
		assertEquals("User retrieved should match the one inserted.", user1, userList.get(0));
		
		usersDoa.create(user2);
		usersDoa.create(user3);
		usersDoa.create(user4);
		List<User> userList4= usersDoa.getAllUsers();
		assertEquals("One user should be created and retrieved.", 4, userList4.size());
	
	}
	
	
	@Test
	public void testCreatUser(){
		User user = new User("fengfeng","Feng Yang","fengfeng", true,"ROLE_ADMIN","feng@gmail.com");
		 usersDoa.create(user);
		List<User> users = usersDoa.getAllUsers();
		assertEquals("Number of users should be 1", 1, users.size());
		assertTrue("User should exist.", usersDoa.exists(user.getUsername()));
		assertFalse("User should not exist.", usersDoa.exists("lioninesxs"));
		assertEquals("Created user should be identical to retrieved user.", user, users.get(0));
	}

}
