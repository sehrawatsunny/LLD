package com.practice.design.Logger.sink;

import com.practice.design.Logger.core.LogEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public final class FileSink implements LogSink {
    private final Path path;
    public FileSink(Path path) { this.path = path; }
    public void log(LogEvent e) {
        try (var out = Files.newBufferedWriter(path, APPEND, CREATE)) {
            out.write("[%s] %s%n".formatted(e.level(), e.message()));
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
