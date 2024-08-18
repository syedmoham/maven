package com.syedm.app.controller;

import com.syedm.app.repository.MessageRepository;
import com.syedm.app.service.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
public class MessageController {
    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    List<String> getMessages() throws Exception {
        logger.info("RestController in action");
        return messageService.getMessages();
    }
}
