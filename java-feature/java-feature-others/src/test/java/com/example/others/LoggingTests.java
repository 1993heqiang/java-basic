package com.example.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class LoggingTests {
    @Test
    void simpleTest() throws Exception{
        URL resource = LoggingTests.class.getResource("/root.log");
        if(resource == null) {
            Assertions.fail("root.log not exists.");
        }
        Path logFilePath = Paths.get(resource.getFile());
        PrintStream printStream = new PrintStream(Files.newOutputStream(logFilePath));
        System.setErr(printStream);

        Logger logger = Logger.getLogger(LoggingTests.class.getSimpleName());
        String errorMsg = "Boom!";
        logger.log(Level.SEVERE, errorMsg);

        List<String> logs = Files.readAllLines(logFilePath);
        boolean containsErrorMsg = logs.stream().anyMatch(log -> log.contains(errorMsg));
        Assertions.assertTrue(containsErrorMsg);
    }
}
