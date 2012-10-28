package org.kambanaria.writebytecode.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassWriter;

public class DemoClassAdapter extends ClassAdapter{

    public DemoClassAdapter() {
        super(new ClassWriter(true, true));
    }
       
    public ClassWriter getCw(){
        return (ClassWriter)cv;
    }

}
