package org.kambanaria.writebytecode.asm;

public class DemoAddInterface extends DemoClassAdapter {
    
    @Override
    public void visit(int version, int access, String name, String superName, String[] interfaces, String sourceFile) {
        super.visit(version, access, name, superName, new String[]{"java/lang/Cloneable"}, sourceFile);
    }

}
