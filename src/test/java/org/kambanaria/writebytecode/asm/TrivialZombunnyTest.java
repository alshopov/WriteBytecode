package org.kambanaria.writebytecode.asm;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TrivialZombunnyTest {
    
    Zombunny sut;
    
    @Before
    public void setUp() {
        sut = new Zombunny();
    }
    
    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testGetValue() {
        assertEquals("Wrong value!", Integer.valueOf(1), sut.getValue());
    }
}
