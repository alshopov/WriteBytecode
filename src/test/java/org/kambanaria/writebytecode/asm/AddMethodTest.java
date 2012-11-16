package org.kambanaria.writebytecode.asm;

import java.io.IOException;
import java.lang.reflect.Constructor;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class AddMethodTest {

    Comparable sut2;

    Comparable sut3;
    
    Comparable sut4;

    @Before
    public void setUp() throws IOException, ReflectiveOperationException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new AddField(new ManipulateConstructors(new AddMethod(new AddInterface(cw))));
        ClassReader rdr = new ClassReader(Utilities.CLASS_NAME);
        rdr.accept(cv, 0);
        byte[] newClassBytes = cw.toByteArray();
        StupidClassLoader ldr = new StupidClassLoader();
        ldr.provide(Utilities.CLASS_NAME, newClassBytes);
        Class<?> newClass = ldr.loadClass(Utilities.CLASS_NAME);
        Constructor<?> constructor = newClass.getDeclaredConstructor(Integer.class);
        sut2 = (Comparable) constructor.newInstance(new Integer(2));
        sut3 = (Comparable) constructor.newInstance(new Integer(3));
        sut4 = (Comparable) constructor.newInstance(new Integer(4));
    }

    @After
    public void tearDown() {
        sut2 = null;
        sut3 = null;
        sut4 = null;
    }

    @Test
    public void test2LessThan3() throws ReflectiveOperationException {
        System.out.println(sut2);
        //assertThat(sut2, lessThan(sut3));
    }

    @Test
    public void test2Equals2() throws ReflectiveOperationException {
        //assertThat(sut2, comparesEqualTo(sut2));
    }
}
