package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;

public class AddMethod extends DemoClassAdapter {

    public AddMethod(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public void visitEnd() {

        MethodVisitor compareToObject = cv.visitMethod(ACC_PUBLIC, "compareTo", "(Ljava/lang/Object;)I", null, null);
        compareToObject.visitCode();
        compareToObject.visitVarInsn(ALOAD, 1);
        compareToObject.visitTypeInsn(INSTANCEOF, "org/kambanaria/writebytecode/asm/Zombunny");
        Label lbl0 = new Label();
        compareToObject.visitJumpInsn(IFNE, lbl0);
        compareToObject.visitTypeInsn(NEW, "java/lang/ClassCastException");
        compareToObject.visitInsn(DUP);
        compareToObject.visitMethodInsn(INVOKESPECIAL, "java/lang/ClassCastException", "<init>", "()V");
        compareToObject.visitInsn(ATHROW);
        compareToObject.visitLabel(lbl0);
        compareToObject.visitVarInsn(ALOAD, 0);
        compareToObject.visitVarInsn(ALOAD, 1);
        compareToObject.visitTypeInsn(CHECKCAST, "org/kambanaria/writebytecode/asm/Zombunny");
        compareToObject.visitMethodInsn(INVOKEVIRTUAL, "org/kambanaria/writebytecode/asm/Zombunny", "compareTo", "(Lorg/kambanaria/writebytecode/asm/Zombunny;)I");
        compareToObject.visitInsn(IRETURN);
        compareToObject.visitMaxs(2, 2);
        compareToObject.visitEnd();

        MethodVisitor compareToZombunny = cv.visitMethod(ACC_PUBLIC, "compareTo", "(Lorg/kambanaria/writebytecode/asm/Zombunny;)I", null, null);
        compareToZombunny.visitCode();
        compareToZombunny.visitVarInsn(ALOAD, 0);
        compareToZombunny.visitFieldInsn(GETFIELD, "org/kambanaria/writebytecode/asm/Zombunny", "_version", "Ljava/lang/Integer;");
        compareToZombunny.visitVarInsn(ALOAD, 1);
        compareToZombunny.visitFieldInsn(GETFIELD, "org/kambanaria/writebytecode/asm/Zombunny", "_version", "Ljava/lang/Integer;");
        compareToZombunny.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "compareTo", "(Ljava/lang/Integer;)I");
        compareToZombunny.visitInsn(IRETURN);
        compareToZombunny.visitMaxs(2, 2);
        compareToZombunny.visitEnd();
        
        cv.visitEnd();
    }
}
