package org.kambanaria.writebytecode.asm;


public class DemoClassLoader extends ClassLoader {
    private String _demoName;

    public DemoClassLoader(String demoName) {
        super(DemoClassLoader.class.getClassLoader());
        _demoName = demoName;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("org.kambanaria.writebytecode.asm.SimpleClass")) {
            try {
                byte[] bytes = null; // FIXME
                return defineClass(name, bytes, 0, bytes.length);
            } catch (ClassFormatError e) {
                throw new ClassNotFoundException(e.getMessage(), e);
            }
        } else {
            return getParent().loadClass(name);
        }
    }

}