package com.syedm.app.service;

import com.syedm.app.repository.MessageRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements  MessageService {
    protected final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private MessageRepository messageRepository;

    public List<String> getMessages() throws Exception {
        logger.info("Service in action");
        return messageRepository.getMessages();
    }

    public void logNormalThread()  {
        logger.info("service - normal thread");
    }

    @Async
    public void logAsyncThread()  {
        logger.info("service - async thread");
    }
}
