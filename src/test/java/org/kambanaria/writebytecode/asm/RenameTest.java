package org.kambanaria.writebytecode.asm;

import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class RenameTest {

    Object sut;
    static final String suffix = "1";
    static final String newName = Utilities.CLASS_NAME + suffix;

    @Before
    public void setUp() throws IOException, ReflectiveOperationException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new Rename(cw, suffix);
        ClassReader rdr = new ClassReader(Utilities.CLASS_NAME);
        rdr.accept(cv, 0);
        byte[] renamed = cw.toByteArray();
        StupidClassLoader ldr = new StupidClassLoader();
        ldr.provide(newName, renamed);
        Class<?> renamedClass = ldr.loadClass(newName);
        sut = renamedClass.newInstance();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testRenamedClass() {
        String resultName = sut.getClass().getName();
        System.out.println("Renamed to " + resultName);
        assertEquals("The names differ: ",newName, resultName);
    }
}
