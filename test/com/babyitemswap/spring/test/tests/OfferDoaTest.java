package com.babyitemswap.spring.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import com.babyitemswap.spring.DOA.Offer;
import com.babyitemswap.spring.DOA.OfferDoa;
import com.babyitemswap.spring.DOA.User;
import com.babyitemswap.spring.DOA.UsersDoa;
@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/babyitemswap/spring/config/dao-context.xml",
		"classpath:com/babyitemswap/spring/config/security-context.xml",
		"classpath:com/babyitemswap/spring/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDoaTest {
	
		@Autowired
		private OfferDoa offerDoa;
		@Autowired
		private DataSource dataSource;
		@Autowired
		private UsersDoa usersDoa;
		
		private User user1 = new User("LeeShyu111","Feng Yang","fengfeng", true,"ROLE_ADMIN","feng1@gmail.com");
		private User user2 = new User("fenfeng2","Fen2 Yan","feng1234", true,"ROLE_USER","feng2@gmail.com");
		private User user3 = new User("fengfeng3","Feng3 Yang","fengfeng2", true,"ROLE_USER","feng3@gmail.com");
		private User user4 = new User("fengfeng4","Feng4 Yang","fengfeng3", false,"ROLE_USER","feng4@gmail.com");
		
		private Offer offer1 = new Offer(user1, "This is a test offer1 offer1 offer1 offer1");
		private Offer offer2 = new Offer(user1, "This is a test offer1 offer1 offer1 offer2");
		private Offer offer3 = new Offer(user2, "This is a test offer1 offer1 offer1 offer3");
		private Offer offer4 = new Offer(user3, "This is a test offer1 offer1 offer1 offer4");
		private Offer offer5 = new Offer(user3, "This is a test offer1 offer1 offer1 offer5");
		private Offer offer6 = new Offer(user3, "This is a test offer1 offer1 offer1 offer6");
		private Offer offer7 = new Offer(user4, "This is a test offer1 offer1 offer1 offer7");
		
		
		
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
			
			offerDoa.saveOrUpdate(offer1);
			List<Offer> offerList = offerDoa.getOffers();
			assertEquals("Should b 1 offers for enabled users", 1, offerList.size());
			assertEquals("Created offer should be identical to retrieved offer.", offer1, offerList.get(0));
			offerDoa.saveOrUpdate(offer2);
			offerDoa.saveOrUpdate(offer3);
			offerDoa.saveOrUpdate(offer4);
			offerDoa.saveOrUpdate(offer5);
			offerDoa.saveOrUpdate(offer6);
			offerDoa.saveOrUpdate(offer7);

			List<Offer> offers = offerDoa.getOffers();
			assertEquals("Should be six offers for enabled users", 6, offers.size());
			
		}
		@Test
		public void testGetByUsername()
		{
			usersDoa.create(user1);
			usersDoa.create(user2);
			usersDoa.create(user3);
			usersDoa.create(user4);
			
			offerDoa.saveOrUpdate(offer1);
			offerDoa.saveOrUpdate(offer2);
			offerDoa.saveOrUpdate(offer3);
			offerDoa.saveOrUpdate(offer4);
			offerDoa.saveOrUpdate(offer5);
			offerDoa.saveOrUpdate(offer6);
			offerDoa.saveOrUpdate(offer7);

			List<Offer> offers1 = offerDoa.getOffers(user3.getUsername());
			assertEquals("should be 3", 3, offers1.size());
			List<Offer> offers2 = offerDoa.getOffers("xdjm");
			assertEquals("should be 2", 0, offers2.size());
			List<Offer> offers3 = offerDoa.getOffers(user2.getUsername());
			assertEquals("should be 1", 1, offers3.size());
			
			
		}
		@Test
		public void testDelete()
		{
			usersDoa.create(user1);
			usersDoa.create(user2);
			usersDoa.create(user3);
			usersDoa.create(user4);
			
			offerDoa.saveOrUpdate(offer1);
			offerDoa.saveOrUpdate(offer2);
			offerDoa.saveOrUpdate(offer3);
			offerDoa.saveOrUpdate(offer4);
			offerDoa.saveOrUpdate(offer5);
			offerDoa.saveOrUpdate(offer6);
			offerDoa.saveOrUpdate(offer7);
			
			Offer retrieved  = offerDoa.getOffers(offer2.getId());
			assertNotNull("Offer with ID"+retrieved.getId() +" should not be null.", retrieved);
			offerDoa.delete(offer2.getId());
			
			Offer retrieved2  = offerDoa.getOffers(offer2.getId());
			assertNull("Offer with ID"+retrieved.getId() +" should be null.", retrieved2);
		}
		
		@Test
		public void testGetByID()
		{
			usersDoa.create(user1);
			usersDoa.create(user2);
			usersDoa.create(user3);
			usersDoa.create(user4);
			
			offerDoa.saveOrUpdate(offer1);
			offerDoa.saveOrUpdate(offer2);
			offerDoa.saveOrUpdate(offer3);
			offerDoa.saveOrUpdate(offer4);
			offerDoa.saveOrUpdate(offer5);
			offerDoa.saveOrUpdate(offer6);
			offerDoa.saveOrUpdate(offer7);
			
			Offer retrieved1=offerDoa.getOffers(offer1.getId());
			assertEquals("Offers should match", offer1, retrieved1);
			Offer retrieved7 = offerDoa.getOffers(offer7.getId());
			assertNull("Should not retrieve offer from table",retrieved7);
		}
		
		@Test
		public void testUpdate()
		{
			usersDoa.create(user1);
			usersDoa.create(user2);
			usersDoa.create(user3);
			usersDoa.create(user4);
			
			offerDoa.saveOrUpdate(offer1);
			offerDoa.saveOrUpdate(offer2);
			offerDoa.saveOrUpdate(offer3);
			offerDoa.saveOrUpdate(offer4);
			offerDoa.saveOrUpdate(offer5);
			offerDoa.saveOrUpdate(offer6);
			offerDoa.saveOrUpdate(offer7);

			offer3.setText("This is updated text for offer3.");
			offerDoa.saveOrUpdate(offer3);
			Offer retrieved = offerDoa.getOffers(offer3.getId());
			assertEquals("Retrieved offer shold be update",offer3, retrieved);
			System.out.println(retrieved.getText());
		}
		
		
		
		@Test
		public void testCreatOffer(){
			User user=new User("fengfeng","Feng Yang","fengfeng",true, "ROLE_USER", "feng@gmail.com");
			usersDoa.create(user);
			Offer offer = new Offer(user,"Hello Hello Hello Hello Hello Hello Hello Hello");
			 offerDoa.saveOrUpdate(offer);
			List<Offer> offers = offerDoa.getOffers();
			assertEquals("Number of offers should be 1", 1, offers.size());
			assertEquals("Created offer should be identical to retrieved offer.", offer, offers.get(0));
		
			offer = offers.get(0);
			offer.setText("Updated offer text. Updated offer text.");
			 offerDoa.saveOrUpdate(offer);
			Offer updated = offerDoa.getOffers(offer.getId());
			assertEquals("Updated offer should match retreived updated offer", offer, updated);
			
			//test get by ID
			Offer offer2 = new Offer(user, "This is a test offer.This is a test offer.");
			 offerDoa.saveOrUpdate(offer2);
			
			List<Offer> userOffers = offerDoa.getOffers(user.getUsername());
			assertEquals("Should be two offers for user",2,userOffers.size());
			
			List<Offer> secondList = offerDoa.getOffers();
			
			for(Offer current: secondList)
			{
				Offer retrieved = offerDoa.getOffers(current.getId());
				assertEquals("Offer by Id should match offer from the list", current, retrieved);
			}
			
			offerDoa.delete(offer.getId());
			List<Offer> empty =offerDoa.getOffers();
			assertEquals("Offers list should be empty",1,empty.size());
			
			
			
			
		}

}
