package com.babyitemswap.spring.test.tests;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.babyitemswap.spring.DOA.Message;
import com.babyitemswap.spring.DOA.MessageDoa;
import com.babyitemswap.spring.DOA.User;
import com.babyitemswap.spring.DOA.UsersDoa;
@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/babyitemswap/spring/config/dao-context.xml",
		"classpath:com/babyitemswap/spring/config/security-context.xml",
		"classpath:com/babyitemswap/spring/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDoaTest {
	
		@Autowired
		private MessageDoa messageDoa;
		@Autowired
		private DataSource dataSource;
		@Autowired
		private UsersDoa usersDoa;
		
		private User user1 = new User("LeeShyu111","Feng Yang","fengfeng", true,"ROLE_ADMIN","feng1@gmail.com");
		private User user2 = new User("fenfeng2","Fen2 Yan","feng1234", true,"ROLE_USER","feng2@gmail.com");
		private User user3 = new User("fengfeng3","Feng3 Yang","fengfeng2", true,"ROLE_USER","feng3@gmail.com");
		private User user4 = new User("fengfeng4","Feng4 Yang","fengfeng3", false,"ROLE_USER","feng4@gmail.com");
		

		
		
		@Before
		public void init(){
			JdbcTemplate jdbc = new JdbcTemplate(dataSource);
			jdbc.execute("delete from offers");
			jdbc.execute("delete from messages");
			jdbc.execute("delete from users");
			
		}
		@Test
		public void testCreate(){
			usersDoa.create(user1);
			usersDoa.create(user2);
			usersDoa.create(user3);
			usersDoa.create(user4);
			
			Message message1=new Message("Subject1","Hello there",user2.getName(),user2.getEmail(),user3.getUsername());
			System.out.println(message1);
			messageDoa.saveOrUpdate(message1);
		}

}
