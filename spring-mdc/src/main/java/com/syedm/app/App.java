package com.syedm.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Hello world!
 *
 */
@EnableAsync
@SpringBootApplication
public class App  {
    protected static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )    {
        // ThreadContext.put("username", "Mohammad Syed");
        MDC.put("username", "Mohammad Syed");
        logger.info("app main started");
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Executor asyncExecutor()  {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.setTaskDecorator(new MdcTaskDecorator());
        return executor;
    }
}
