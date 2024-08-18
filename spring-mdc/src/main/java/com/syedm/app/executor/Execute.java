package com.syedm.app.executor;

import com.syedm.app.service.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Execute implements CommandLineRunner {
    private final Logger logger = LogManager.getLogger(Execute.class);

    @Autowired
    private MessageService messageService;


    @Override
    public void run(String... args) throws Exception {
        logger.info("line from run commandlinerunner");
        messageService.logNormalThread();
        messageService.logAsyncThread();
    }

}
