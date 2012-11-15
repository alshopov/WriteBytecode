package org.kambanaria.writebytecode.asm;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ZombunnyTest {

    Object sut;


    @Before
    public void setUp() throws ReflectiveOperationException {
        sut = Class.forName(Utilities.CLASS_NAME).newInstance();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testReflectiveGetVersion() throws ReflectiveOperationException {
        Integer value = (Integer) Utilities.call0ArgsMethodOn(sut, Utilities.METHOD_NAME);
        assertEquals("Wrong value!", Integer.valueOf(1), value);
    }
}
