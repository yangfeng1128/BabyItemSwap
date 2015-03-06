package com.babyitemswap.spring.DOA;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
@Component("userDao")
public class UsersDoa {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public UsersDoa(){
		System.out.println("Successfully loaded OfferDOA");
	}
	
	
	
    
	@Transactional
	public void create(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
		
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		
//		params.addValue("username", user.getUsername());
//		params.addValue("name", user.getName());
//		params.addValue("password", passwordEncoder.encode(user.getUsername()));
//		params.addValue("email", user.getEmail());
//		params.addValue("enabled", user.isEnabled());
//		params.addValue("authority", user.getAuthority());
//		
//		
//		return jdbc.update("insert into users (username, name, password, email, enabled, authority) values (:username,:name,:password,:email,:enabled,:authority)", params)==1;
//		
	}
	public boolean exists(String username) {
		return getUser(username) != null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
		//return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
	}

	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		//crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.idEq(username));
		return (User)crit.uniqueResult();
	}

	
	
}
