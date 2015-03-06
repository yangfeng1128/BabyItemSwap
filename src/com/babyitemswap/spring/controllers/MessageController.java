package com.babyitemswap.spring.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babyitemswap.spring.DOA.Message;
import com.babyitemswap.spring.service.MessageService;

@Controller
public class MessageController {
    @Autowired
	private MessageService messageService;

	@RequestMapping(value="/getmessages", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {
		
		List<Message> messages = null;
		
		if(principal == null) {
			messages = new ArrayList<Message>();
		}
		else {
			String username = principal.getName();
			messages = messageService.getMessages(username);
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		
		return data;
	}
	@RequestMapping(value="/messages",method=RequestMethod.GET)
	public String getMessages(Model model,Principal principal) {
		List<Message> messages=null;
		if(principal==null)
		{
			messages= new ArrayList<Message>();
		} else {
		String username=principal.getName();
		messages=messageService.getMessages(username);
		}
		model.addAttribute("messages",messages);
		return "messages";
	   }
	
	
	@RequestMapping(value="/showmessage", method=RequestMethod.GET)
	public String showMessage(Model model, @RequestParam("id") Integer id)
	{
		Message message=null;
		Integer messageId=id;
		message = messageService.getMessages(messageId);
		model.addAttribute("message",message);
		return "showmessage";
	}

}
