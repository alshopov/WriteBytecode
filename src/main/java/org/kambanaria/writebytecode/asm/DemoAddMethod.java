package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;

public class DemoAddMethod extends DemoClassAdapter {

    public DemoAddMethod(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public void visitEnd() {
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "compareTo", "(java/lang/Object)I", null, null); // TODO: Check generics
        // FIXME - Real implementation
        mv.visitIntInsn(SIPUSH, 1001);
        mv.visitInsn(IRETURN);
        mv.visitMaxs(1, 0);
        mv.visitEnd();
    }
}
