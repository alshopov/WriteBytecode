package org.kambanaria.writebytecode.asm;

import java.io.IOException;
import java.lang.reflect.Constructor;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ChimerizeTest {

    Comparable sut;

    @Before
    public void setUp() throws IOException, ReflectiveOperationException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES + //
                ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new AddField(new ManipulateConstructors(//
                new AddMethod(new AddInterface(new Chimerize(cw)))));
        ClassReader rdr = new ClassReader(Utilities.CLASS_NAME);
        rdr.accept(cv, 0);
        byte[] newClassBytes = cw.toByteArray();
        StupidClassLoader ldr = new StupidClassLoader();
        ldr.provide(Utilities.CLASS_NAME, newClassBytes);
        Class<?> newClass = ldr.loadClass(Utilities.CLASS_NAME);
        Constructor<?> constructor = newClass.getDeclaredConstructor(Integer.class);
        sut = (Comparable) constructor.newInstance(new Integer(42));
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void test_toString() throws ReflectiveOperationException {
        assertEquals("Version: 42", sut.toString());
    }
}
