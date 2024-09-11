package com.chat.Controllers;

import com.chat.Entities.Message;
import com.chat.Entities._user;
import com.chat.Repositories.MessageRepository;
import com.chat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/sendMessage")
    @SendTo("/msg/public")
    public Message sendMessage(
            @Payload Message message,
            SimpMessageHeaderAccessor headerAccessor
    ) { 
       System.out.println(message);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @MessageMapping("/addUser")
    @SendTo("/msg/users")
    public _user addUser(
            @Payload _user user,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        return userRepository.save(user);
    }
}
