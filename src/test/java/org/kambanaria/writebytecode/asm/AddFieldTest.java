package org.kambanaria.writebytecode.asm;

import java.io.IOException;
import java.lang.reflect.Field;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

public class AddFieldTest {

    Object sut;

    @Before
    public void setUp() throws IOException, ReflectiveOperationException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new AddField(cw);
        ClassReader rdr = new ClassReader(Utilities.CLASS_NAME);
        rdr.accept(cv, 0);
        byte[] newClassBytes = cw.toByteArray();
        StupidClassLoader ldr = new StupidClassLoader();
        ldr.provide(Utilities.CLASS_NAME, newClassBytes);
        Class<?> newClass = ldr.loadClass(Utilities.CLASS_NAME);
        sut = newClass.newInstance();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testAddedFieild() throws ReflectiveOperationException {
        Field versionFld = sut.getClass().getDeclaredField("_version");
        versionFld.setAccessible(true);
        Object newVersion = versionFld.get(sut);
        System.out.println(Type.getDescriptor(Integer.class));
        assertEquals(null, newVersion);
    }

}
