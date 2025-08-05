package com.practice.design.Logger.sink;

import com.practice.design.Logger.core.LogEvent;

public interface LogSink {
    void log(LogEvent event);

    default boolean supportsAsync() {
        return false;
    }
}
