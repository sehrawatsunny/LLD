package com.practice.design.Logger.sink;

import com.practice.design.Logger.core.LogEvent;

public final class ConsoleSink implements LogSink {
    @Override
    public void log(LogEvent event) {
        System.out.printf("[%s] %s%n", event.level(), event.message());
    }
}
