package com.babyitemswap.spring.DOA;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Component("messageDoa")
@Transactional
public class MessageDoa {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	public MessageDoa(){
		
	}
	
  
	
	
//	@Transactional
//	public int[] create(List<Offer> offers)
//	{
//		SqlParameterSource[] params=SqlParameterSourceUtils.createBatch(offers.toArray());
//		return jdbc.batchUpdate("insert into offers(username, text) values(:username, :text)", params);
//	}

	public void saveOrUpdate(Message message)
	{
		System.out.println(message);
		session().saveOrUpdate(message);
//		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
//		return jdbc.update("insert into offers(username, text) values(:username, :text)", params)==1;
	}
    public boolean delete(int id)
    {
    	Query query =session().createQuery("delete from Message where id=:id");
    	query.setLong("id",id);
    	return query.executeUpdate()==1;
//    	MapSqlParameterSource params = new MapSqlParameterSource("id", id);
//    	return jdbc.update("delete from offers where id=:id", params)==1;
    }
    @SuppressWarnings("unchecked")
	public List<Message> getMessages() {
	//	MapSqlParameterSource params = new MapSqlParameterSource();
	//	params.addValue("name", "Mike");
	//	return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true", new OfferRowMapper());
	Criteria crit = session().createCriteria(Message.class);
	return crit.list();
	}
    @SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {
		//	MapSqlParameterSource params = new MapSqlParameterSource();
		//	params.addValue("name", "Mike");
		//	return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true and offers.username=:username",new MapSqlParameterSource("username",username), new OfferRowMapper());
	Criteria crit = session().createCriteria(Message.class);
	
	crit.add(Restrictions.eq("username", username));
	
	return crit.list();
	
	}
	
	public Message getMessages(int id) {
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("id", id);
//		return jdbc.queryForObject("select * from offers, users where offers.username=users.username and users.enabled=true and id= :id",params, new OfferRowMapper());
		Criteria crit = session().createCriteria(Message.class);
		crit.add(Restrictions.idEq(id));
		return (Message)crit.uniqueResult();
		
	}
	
	
	
}
