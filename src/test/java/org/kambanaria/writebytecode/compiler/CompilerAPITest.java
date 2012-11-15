package org.kambanaria.writebytecode.compiler;

import java.lang.reflect.Method;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompilerAPITest {

    Object i;
    Object you;

    @BeforeClass
    public static void setUpClass() throws Exception {
        CompilerAPI.main(null);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws ReflectiveOperationException {
        i = Class.forName("I").newInstance();
        you = Class.forName("You").newInstance();
    }

    @After
    public void tearDown() {
        i = null;
        you = null;
    }

    @Test
    public void testMain() throws Exception {
        assertEquals(i.getClass().getName(), "I");
        assertEquals(you.getClass().getName(), "You");
        Method m1 = i.getClass().getMethod("singOutOfTune", (Class<?>[]) null);
        Object didI = m1.invoke(i, (Object[]) null);
        
        for (Method m2 : you.getClass().getMethods()) {
            if ("wouldDo".equals(m2.getName())) {
                Class<?>[] args = m2.getParameterTypes();
                if (1 == args.length && args[0].isAssignableFrom(boolean.class)) {
                    System.out.println("m2.invoke(you, didI)");
                    return;
                }
            }
        }
        fail("We did not find our method!");
    }
}
