package com.practice.design.Logger.observer;

import com.practice.design.Logger.core.LogEvent;
import com.practice.design.Logger.core.LogLevel;
import com.practice.design.Logger.sink.LogSink;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SinkSubject {
    private final ConcurrentMap<LogLevel , List<LogSink>> sinks = new ConcurrentHashMap<>();

    public void addSink(LogLevel level, LogSink sink) {
        sinks.computeIfAbsent(level, k -> new ArrayList<>()).add(sink);
    }

    public void publish(LogEvent logEvent){
        LogLevel level = logEvent.level();
        sinks.getOrDefault(level, List.of())
                .forEach(sink -> sink.log(logEvent));
    }
}
