package org.kambanaria.writebytecode.asm;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.objectweb.asm.ClassReader;

public final class Utilities {

    private Utilities() {
    }

    public static String toClassPathResourceName(String clazz) {
        return clazz.replace('.', '/') + ".class";
    }

    public static byte[] retrieveBytesFromClassPath(String className) throws IOException {
        String path = toClassPathResourceName(className);
        InputStream is = Utilities.class.getClassLoader().getResourceAsStream(path);
        InputStream classBytes = new BufferedInputStream(is);
        return is2bytes(classBytes);
    }

    public static byte[] patch(byte[] bytes, DemoClassAdapter adapter) {
        ClassReader cr = new ClassReader(bytes);
        cr.accept(adapter, ClassReader.SKIP_FRAMES);
        return adapter.getCw().toByteArray();
    }

    public static byte[] is2bytes(InputStream is) throws IOException {
        int max = 1024 * 1024;
        byte[] bytes = new byte[max]; // 1MB
        int read = is.read(bytes); // Don't do that
        byte[] result = new byte[read];
        System.arraycopy(bytes, 0, result, 0, result.length);
        return result;
    }

    public static Object call0ArgsMethodOn(Object o, String methodName) throws ReflectiveOperationException {
        Class<?> c = o.getClass();
        Method m = c.getDeclaredMethod(methodName, (Class<?>[]) null);
        m.setAccessible(true);
        return m.invoke(o, (Object[]) null);

    }
}
