package com.example.jcommander;

import com.beust.jcommander.JCommander;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JCommanderTest {
    @Test
    void demo1() {
        Args args = new Args();
        String[] argv = { "-log", "2", "-groups", "unit" };
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        Assertions.assertEquals(2, args.getVerbose().intValue());
    }

    @Test
    void passwordDemo() {
        ArgsPassword argsPassword = new ArgsPassword();
        String[] argv = {"-password", "123"};
        JCommander.newBuilder()
                .addObject(argsPassword)
                .build()
                .parse(argv);
        Assertions.assertEquals("123", argsPassword.getPassword());
    }
}
