package com.example.task01;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Logger {
    private final String name;

    public enum Level{
        DEBUG, INFO, WARNING, ERROR
    }

    private static Map<String, Logger> loggers = new HashMap<>();

    private Level currentLevel = Level.DEBUG;

    private Logger(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public static Logger getLogger(String name){
        if (!loggers.containsKey(name)){
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }
    public void setLevel(Level level){
        this.currentLevel = level;
    }
    public Level getLevel(){
        return currentLevel;
    }
    public void log(Level level, String message){
        if (level.ordinal() >= currentLevel.ordinal()){
            printLog(level, message);
        }
    }

    public void log(Level level, String template, Object... args){
        if (level.ordinal() >= currentLevel.ordinal()){
            String formattedMessage = MessageFormat.format(template, args);
            printLog(level, formattedMessage);
        }
    }
    private void printLog(Level level, String message){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        System.out.println(String.format("[%s] %s %s %s - %s", level, date, time, name, message));
    }

    public void debug(String template, Object... args){
        log(Level.DEBUG, template, args);
    }
    public void debug(String message){
        log(Level.DEBUG, message);
    }
    public void info(String template, Object... args){
        log(Level.INFO, template, args);
    }
    public void info(String message){
        log(Level.INFO, message);
    }
    public void warning(String template, Object... args){
        log(Level.WARNING, template, args);
    }
    public void warning(String message){
        log(Level.WARNING, message);
    }
    public void error(String template, Object... args){
        log(Level.ERROR, template, args);
    }
    public void error(String message){
        log(Level.ERROR, message);
    }
}