package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;
public class ManipulateConstructors extends DemoClassAdapter {

    public ManipulateConstructors(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
        if ("<init>".equals(name)){
            MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/lang/Integer");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_2);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Integer", "<init>", "(I)V");
            mv.visitFieldInsn(PUTFIELD, "org/kambanaria/writebytecode/asm/Zombunny", "_version", "Ljava/lang/Integer;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 1);
            mv.visitEnd();
            return mv;
        } else {
            return cv.visitMethod(access, name, desc, signature, exceptions);
        }
    }
}
