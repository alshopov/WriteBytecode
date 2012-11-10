package org.kambanaria.writebytecode.asm;

import java.util.HashMap;
import java.util.Map;

public class StupidClassLoader extends ClassLoader {

    private Map<String, byte[]> bytes = new HashMap<String, byte[]>();

    public StupidClassLoader() {
        super(StupidClassLoader.class.getClassLoader());
    }

    public void provide(String className, byte[] classBytes) {
        bytes.put(className, classBytes);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        byte[] classBytes = bytes.get(name);
        Class<?> loaded;
        if (classBytes != null) {
            loaded = defineClass(name, classBytes, 0, classBytes.length);
        } else {
            ClassLoader parent = getParent();
            if (null == parent) {
                parent = ClassLoader.getSystemClassLoader();
            }
            loaded = parent.loadClass(name);
        }
        return loaded;
    }
}