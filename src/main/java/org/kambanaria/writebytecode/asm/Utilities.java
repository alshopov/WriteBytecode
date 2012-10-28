package org.kambanaria.writebytecode.asm;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.attrs.Attributes;

public class Utilities {

    public static final String toClassPathResourceName(String clazz) {
        return clazz.replace('.', '/') + ".class";
    }

    public static final byte[] retrieveBytesFromClassPath(String className) throws IOException {
        String path = toClassPathResourceName(className);
        InputStream is = Utilities.class.getClassLoader().getResourceAsStream(path);
        InputStream classBytes = new BufferedInputStream(is);
        return is2bytes(classBytes);
    }

    public static final byte[] patch(byte[] bytes, DemoClassAdapter adapter) {
        ClassReader cr = new ClassReader(bytes);
        cr.accept(adapter, Attributes.getDefaultAttributes(), true);
        return adapter.getCw().toByteArray();
    }

    public static final byte[] is2bytes(InputStream is) throws IOException {
        int max = 1024 * 1024;
        byte[] bytes = new byte[max]; // 1MB
        int read = is.read(bytes); // Don't do that
        byte[] result = new byte[read];
        System.arraycopy(bytes, 0, result, 0, result.length);
        return result;
    }

    public static byte[] demo(String name) throws IOException {
        byte[] original = retrieveBytesFromClassPath(SimpleClass.class.getName());
        byte result[] = null;
        if (name.equals("DemoAddMethod")){
            result = patch(original, new DemoAddMethod());
        } else if (name.equals("DemoAddInterface")){
            result = patch(original, new DemoAddInterface());
        } else if (name.equals("DemoChangeMethod")){
            result = patch(original, new DemoChangeMethod());
        }
        return result;
    }

}
