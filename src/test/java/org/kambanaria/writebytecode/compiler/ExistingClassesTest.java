package org.kambanaria.writebytecode.compiler;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Alexander Shopov <ash@kambanaria.org>
 */
public class ExistingClassesTest {

    @Test
    public void test1000times() {
        int times = 1000;
        do {
            boolean didI;
            I i = new I();
            You you = new You();
            String what = you.wouldDo(didI = i.singOutOfTune());
            assertEquals(what, didI ? You.DID : You.DIDNOT);
        } while (--times > 0);


        // What would you do if I sang out of tune ? The Beatles
    }
}
