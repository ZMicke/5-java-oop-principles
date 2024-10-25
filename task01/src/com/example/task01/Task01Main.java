package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getLogger("testLogger");
        Logger logger2 = Logger.getLogger("testLogger");

        logger1.log(Logger.Level.ERROR, "error complete");
        // Проверим, что это один и тот же объект
        System.out.println();
    }
}
