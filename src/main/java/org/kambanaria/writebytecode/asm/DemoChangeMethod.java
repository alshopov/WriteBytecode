package org.kambanaria.writebytecode.asm;

import static org.objectweb.asm.Constants.*;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.CodeAdapter;
import org.objectweb.asm.CodeVisitor;

public class DemoChangeMethod extends DemoClassAdapter {
    @Override
    public CodeVisitor visitMethod(int access, String name, String desc, String[] exceptions, Attribute attrs) {
        CodeVisitor cv = super.visitMethod(access, name, desc, exceptions, attrs);

        if ("getValue".equals(name) && "()I".equals(desc)) {
            return new MethodVisitor(cv);
        } else {
            return cv;
        }
    }
    
    class MethodVisitor extends CodeAdapter{
        public MethodVisitor(CodeVisitor cv){
            super(cv);
            cv.visitIntInsn(BIPUSH, 42);
            cv.visitInsn(IRETURN);
        }
    }
}
