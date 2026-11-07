package com.example.lib.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Logger {
    private static String[] logs = new String[10];
    private static int logCount = 0;

    public static void log(String action) {
        String logMessage = "[" + LocalDateTime.now() + "] " + action;

        if (logCount == logs.length) {
            logs = Arrays.copyOf(logs, logs.length * 2);
        }
        logs[logCount++] = logMessage;

        try (PrintWriter out = new PrintWriter(new FileWriter("log.txt", true))) {
            out.println(logMessage);
        } catch (IOException e) {
            System.err.println("Ошибка записи в лог-файл: " + e.getMessage());
        }
    }
}

