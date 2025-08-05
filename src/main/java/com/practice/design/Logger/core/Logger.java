package com.practice.design.Logger.core;

import com.practice.design.Logger.config.LogConfigurator;

//Logger façade (thread‑safe singleton)
public final class Logger {
    private static volatile Logger INSTANCE;
    private final LevelHandler chain;

    public Logger(LevelHandler chain) {
        this.chain = chain;
    }

    public static Logger get() {           // simple DCL
        if (INSTANCE == null) {
            synchronized (Logger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LogConfigurator().build(); // default config
                }
            }
        }
        return INSTANCE;
    }
    public void info(String msg) {
        log(LogLevel.INFO, msg);
    }

    public void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    public void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    public void log(LogLevel level, String msg) {
        chain.handle(new LogEvent(level, msg));
    }
}
