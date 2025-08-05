package com.practice.design.Logger;

import com.practice.design.Logger.config.LogConfigurator;
import com.practice.design.Logger.core.LogLevel;
import com.practice.design.Logger.core.Logger;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Step 1: Build logger configuration
        Logger logger = new LogConfigurator()
                .withConsole(LogLevel.INFO)                            // INFO and above to Console
                .withFile(LogLevel.DEBUG, Paths.get("logs/app.log"))  // DEBUG and above to file
                .build();

        // Step 2: Use logger
        logger.info("This is an INFO message.");
        logger.debug("This is a DEBUG message.");
        logger.error("This is an ERROR message.");
    }
}
