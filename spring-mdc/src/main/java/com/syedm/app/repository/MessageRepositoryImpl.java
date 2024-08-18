package com.syedm.app.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public List<String> getMessages() throws Exception {
        logger.info("Repository in action");
        return new ArrayList<String>(Arrays.asList("Hello", "Salaam", "Namaste", "Nee-haw"));
    }
}
