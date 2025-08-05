package com.practice.design.Logger.core;

public class LogLevel {
    public static final LogLevel INFO  = new LogLevel("INFO");
    public static final LogLevel DEBUG = new LogLevel("DEBUG");
    public static final LogLevel ERROR = new LogLevel("ERROR");

    private final String name;

    private LogLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

