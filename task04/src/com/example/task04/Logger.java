package com.example.task04;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Logger {
    private static final HashMap<String, Logger> loggers = new HashMap<>();
    private static ArrayList<MessageHandler> handlers = new ArrayList<>();
    private final String name;
    private ErrorLevel level;

    public Logger(String name) {
        this.name = name;
        this.level = ErrorLevel.INFO;
        handlers.add(new ConsoleHandler());
        loggers.put(this.name, this);
    }

    public Logger(String name, ArrayList<MessageHandler> messageHandlers) {
        this.name = name;
        this.level = ErrorLevel.INFO;
        handlers = messageHandlers;
        loggers.put(this.name, this);
    }

    public Logger(String name, ErrorLevel level, ArrayList<MessageHandler> messageHandlers) {
        this.name = name;
        this.level = level;
        handlers = messageHandlers;
        loggers.put(this.name, this);
    }

    public static Logger getLogger(String name) {
        if (loggers.get(name) == null) loggers.put(name, new Logger(name));
        return loggers.get(name);
    }

    public ErrorLevel getLevel() {
        return level;
    }

    public void setLevel(ErrorLevel level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    private void log(ErrorLevel level, String message) {
        if (this.level.ordinal() <= level.ordinal()) {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
            String printedMessage = MessageFormat.format("[{0}] {1} {2} - {3}", level, date, name, message);

            for (MessageHandler messageHandler : handlers)
                messageHandler.log(printedMessage);

        }
    }

    private void log(ErrorLevel level, String format, Object... args) {
        if (this.level.ordinal() <= level.ordinal()) {
            String message = MessageFormat.format(format, args);

            for (MessageHandler messageHandler : handlers)
                messageHandler.log(message);
        }
    }

    public void info(String message) {
        log(ErrorLevel.INFO, message);
    }

    public void info(String format, Object... objects) {
        log(ErrorLevel.INFO, format, objects);
    }

    public void debug(String message) {
        log(ErrorLevel.DEBUG, message);
    }

    public void debug(String format, Object... objects) {
        log(ErrorLevel.DEBUG, format, objects);
    }

    public void warning(String message) {
        log(ErrorLevel.WARNING, message);
    }

    public void warning(String message, Object... objects) {
        log(ErrorLevel.WARNING, message);
    }


    public void error(String message) {
        log(ErrorLevel.ERROR, message);
    }

    public void error(String format, Object... objects) {
        log(ErrorLevel.ERROR, format, objects);
    }
}