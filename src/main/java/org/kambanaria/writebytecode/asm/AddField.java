package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassVisitor;
import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.Type;

public class AddField extends DemoClassAdapter {

    public AddField(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public void visitEnd() {
        cv.visitField(ACC_PRIVATE, "_version", //
                Type.getDescriptor(Integer.class), null, null);
        cv.visitEnd();
    }
}
