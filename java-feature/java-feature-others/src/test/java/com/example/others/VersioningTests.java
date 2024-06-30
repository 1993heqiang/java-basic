package com.example.others;

import org.junit.jupiter.api.Test;

class VersioningTests {
    @Test
    void versionTest() throws ClassNotFoundException {
        System.out.println(System.getProperty("java.vm.specification.version"));
        Class<?> aClass = Class.forName("java.net.URLClassLoader$3$1");
        System.out.println(aClass);
    }
}