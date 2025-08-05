package com.practice.design.Logger.config;

import com.practice.design.Logger.core.LevelHandler;
import com.practice.design.Logger.core.LogLevel;
import com.practice.design.Logger.core.Logger;
import com.practice.design.Logger.observer.SinkSubject;
import com.practice.design.Logger.sink.ConsoleSink;
import com.practice.design.Logger.sink.FileSink;

import java.nio.file.Path;

/**
 * Purpose: To build and configure a logger with desired log levels and output sinks
 *          using the Chain of Responsibility and Builder patterns.
 *
 * Responsibilities:
 * - Register different log sinks (e.g., Console, File, Database).
 * - Associate each sink with specific log levels (INFO, DEBUG, ERROR).
 * - Construct the Chain of Responsibility of {@link LevelHandler} objects
 *   in the order INFO → DEBUG → ERROR (extendable).
 * - Return a fully configured, ready‑to‑use {@link Logger} instance.
 */
public final class LogConfigurator {

//   Central dispatcher holding the mapping: LogLevel → List<LogSink>.
    private final SinkSubject subject = new SinkSubject();

    public LogConfigurator withConsole(LogLevel level) {
        subject.addSink(level, new ConsoleSink());
        return this;
    }

    public LogConfigurator withFile(LogLevel level, Path path) {
        subject.addSink(level, new FileSink(path));
        return this;
    }

    public Logger build() {
        // Create level‑specific handlers
        LevelHandler infoHandler  = new LevelHandler(LogLevel.INFO,  subject);
        LevelHandler debugHandler = new LevelHandler(LogLevel.DEBUG, subject);
        LevelHandler errorHandler = new LevelHandler(LogLevel.ERROR, subject);

        // Chain the handlers: INFO → DEBUG → ERROR
        infoHandler.setNext(debugHandler).setNext(errorHandler);

        // Return logger with the head of the chain
        return new Logger(infoHandler);
    }
}
