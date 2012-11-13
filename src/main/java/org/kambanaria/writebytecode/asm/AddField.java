package org.kambanaria.writebytecode.asm;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;
import org.objectweb.asm.ClassVisitor;
import static org.objectweb.asm.Opcodes.*;

public class AddField extends DemoClassAdapter {
    public AddField(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public void visitEnd(){
        cv.visitField(ACC_PRIVATE, "_version", Type.getDescriptor(Integer.class), null, null);
        cv.visitEnd();
    }
}
