package org.kambanaria.writebytecode.asm;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ZombunnyTest {

    Object sut;
    private static final String CLASS_NAME = "org.kambanaria.writebytecode.asm.Zombunny";
    private static final String METHOD_NAME = "getValue";

    @Before
    public void setUp() throws ReflectiveOperationException {
        sut = Class.forName(CLASS_NAME).newInstance();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testGetValue() throws ReflectiveOperationException {
        Integer value = (Integer) Utilities.call0ArgsMethodOn(sut, METHOD_NAME);
        assertEquals("Wrong value!", Integer.valueOf(1), value);
    }
}
