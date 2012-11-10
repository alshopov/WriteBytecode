package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class DemoClassAdapter extends ClassVisitor {

    public DemoClassAdapter(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    public ClassWriter getCw() {
        return (ClassWriter) cv;
    }
}
