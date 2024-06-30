package com.example.jopt;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class ShortOptionsTest {
    @Test
    void supportsShortOptions() {
        OptionParser parser = new OptionParser( "aB?*." );

        OptionSet options = parser.parse( "-a", "-B", "-?" );

        assertTrue( options.has( "a" ) );
        assertTrue( options.has( "B" ) );
        assertTrue( options.has( "?" ) );
        assertFalse( options.has( "." ) );
    }

    @Test
    void allowsOptionsToAcceptArguments() {
        OptionParser parser = new OptionParser( "fc:q::" );

        OptionSet options = parser.parse( "-f", "-c", "foo", "-q" );

        assertTrue( options.has( "f" ) );

        assertTrue( options.has( "c" ) );
        assertTrue( options.hasArgument( "c" ) );
        assertEquals( "foo", options.valueOf( "c" ) );
        assertEquals( singletonList( "foo" ), options.valuesOf( "c" ) );

        assertTrue( options.has( "q" ) );
        assertFalse( options.hasArgument( "q" ) );
        assertNull( options.valueOf( "q" ) );
        assertEquals( emptyList(), options.valuesOf( "q" ) );
    }

    @Test
    void acceptsLongOptions() {
        OptionParser parser = new OptionParser();
        parser.accepts( "flag" );
        parser.accepts( "verbose" );

        OptionSet options = parser.parse( "--flag" );

        assertTrue( options.has( "flag" ) );
        assertFalse( options.has( "verbose" ) );
    }
}
