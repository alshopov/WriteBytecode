package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import static org.objectweb.asm.Opcodes.*;

public class ChangeMethod extends DemoClassAdapter {

    public ChangeMethod(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        System.out.println(name);
        System.out.println(desc);
        System.out.println(Utilities.METHOD_NAME.equals(name));
        System.out.println("()Ljava/lang/Integer".equals(desc));
        System.out.println(Utilities.METHOD_NAME.equals(name) && "()Ljava/lang/Integer;".equals(desc));
        if (Utilities.METHOD_NAME.equals(name) && "()Ljava/lang/Integer;".equals(desc)) {
            return new DemoMethodVisitor(Opcodes.ASM4, mv);
        } else {
            return mv;
        }
    }

    class DemoMethodVisitor extends MethodVisitor {

        public DemoMethodVisitor(int version, MethodVisitor mv) {
            super(version, mv);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "org/kambanaria/writebytecode/asm/Zombunny", "_version", "Ljava/lang/Integer;");
            mv.visitInsn(ARETURN);
            mv.visitEnd();

        }
    }
}
