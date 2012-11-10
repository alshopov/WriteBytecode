package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassVisitor;

public class Rename extends DemoClassAdapter {

    private String suffix;

    public Rename(ClassVisitor cv, String suffix) {
        super(cv);
        this.suffix = suffix;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name + suffix, signature, superName, interfaces);
    }
}
