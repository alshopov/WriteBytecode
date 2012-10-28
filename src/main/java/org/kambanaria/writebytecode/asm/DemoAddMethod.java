package org.kambanaria.writebytecode.asm;
import static org.objectweb.asm.Constants.*;

import org.objectweb.asm.CodeVisitor;
public class DemoAddMethod extends DemoClassAdapter {
    
    @Override
    public void visitEnd() {
        CodeVisitor cd = cv.visitMethod(ACC_PUBLIC, "getAnotherValue", "()I", null, null);
        cd.visitIntInsn(SIPUSH, 1001);
        cd.visitInsn(IRETURN);
        cd.visitMaxs(1, 0);
        cv.visitEnd();
    }

}
