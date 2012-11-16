package org.kambanaria.writebytecode.asm;

import java.io.IOException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingMethodAdapter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class Chimerize extends DemoClassAdapter {

    protected ClassNode twig;
    protected MethodNode nm;
    private static final String TWIG_NAME = "org.kambanaria.writebytecode.asm.Version";

    public Chimerize(ClassVisitor cv) throws IOException {
        super(cv);
        ClassReader rdr = new ClassReader(TWIG_NAME);
        twig = new ClassNode();
        rdr.accept(twig, 0);
        for (Object o : twig.methods) {
            MethodNode method = (MethodNode) o;
            if (method.name.equals("toString")) {
                // Guess what is missing?
                nm = method;
            }
        }
    }

    @Override
    public void visitEnd() {
        MethodVisitor chimeric =
                cv.visitMethod(
                nm.access, nm.name, nm.desc,
                nm.signature, null);

        nm.instructions.resetLabels();

        Remapper remapper = new Remapper() {
            @Override
            public String map(String name) {
                return name.replace("Version", "Zombunny");
            }
        };
        
        
        nm.accept(new RemappingMethodAdapter(
                nm.access, nm.desc, chimeric,
                remapper));
        cv.visitEnd();
    }
}
