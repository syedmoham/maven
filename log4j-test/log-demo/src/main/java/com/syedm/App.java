package com.syedm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App  {
    protected static final Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )  {

        ThreadContext.put("username", "ms10347");
        ThreadContext.put("sessionId", "session-123");

        // if level is set to info then trace and debug will not appear
        logger.trace("Entering method processOrder().");
        logger.debug("Received order with ID 12345.");
        logger.info("Order shipped successfully.");
        logger.warn("Potential security vulnerability detected in user input: '...'");
        logger.error("Failed to process order. Error: {. . .}");
        logger.fatal("System crashed. Shutting down...");

        ThreadContext.clearMap();
    }
}
