package com.example.others;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesTests {
    @Test
    void simpleTest() throws BackingStoreException {
        Preferences preferences = Preferences.systemRoot();
        Arrays.stream(preferences.childrenNames()).forEach(System.out::println);
        System.out.println(preferences);
    }
}
