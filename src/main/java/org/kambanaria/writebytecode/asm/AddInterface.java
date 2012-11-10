package org.kambanaria.writebytecode.asm;

import java.util.Arrays;
import org.objectweb.asm.ClassVisitor;

public class AddInterface extends DemoClassAdapter {

    private static final String COMPARABLE = "java/lang/Comparable";

    public AddInterface(ClassVisitor cv){
        super(cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        if (Arrays.asList(interfaces).contains(COMPARABLE)){
           super.visit(version, access, name, signature, superName, interfaces);
        } else {
            int l = interfaces.length;
            String[] newInterfaces = new String[l+1];
            System.arraycopy(interfaces, 0, newInterfaces, 0, l);
            super.visit(version, access, name, signature, superName, interfaces);
        };
    }

}
