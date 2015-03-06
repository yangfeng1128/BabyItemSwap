package com.babyitemswap.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babyitemswap.spring.DOA.Message;
import com.babyitemswap.spring.DOA.MessageDoa;

@Service("messageService")
public class MessageService {
	@Autowired
	private MessageDoa messageDoa;
	

	public List<Message> getMessages(String username) {
		//System.out.println("getting messages");
		return messageDoa.getMessages(username);
	}
	public void sendMessage(Message message) {
		messageDoa.saveOrUpdate(message);
	}

	public Message getMessages(Integer id) {
		
		return messageDoa.getMessages(id);
	}

}