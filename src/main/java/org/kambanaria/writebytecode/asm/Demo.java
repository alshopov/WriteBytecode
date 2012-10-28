package org.kambanaria.writebytecode.asm;

import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] args) throws Exception {
        demoChangeMethod();
        demoAddInterface();
        demoAddMethod();
    }

    public static void demoChangeMethod() throws Exception {
        ClassLoader changeMethod = new DemoClassLoader("DemoChangeMethod");
        Object a = changeMethod.loadClass(SimpleClass.class.getName()).newInstance();
        Method m = a.getClass().getMethod("getValue", (Class[]) null);
        System.out.println("demoChangeMethod: " + m.invoke(a));
    }

    public static void demoAddInterface() throws Exception {
        ClassLoader changeMethod = new DemoClassLoader("DemoAddInterface");
        Object a = changeMethod.loadClass(SimpleClass.class.getName()).newInstance();
        System.out.println("demoAddInterface: " + (a instanceof Cloneable));
    }

    public static void demoAddMethod() throws Exception {
        ClassLoader changeMethod = new DemoClassLoader("DemoAddMethod");
        Object a = changeMethod.loadClass(SimpleClass.class.getName()).newInstance();
        Method m = a.getClass().getMethod("getAnotherValue", (Class[]) null);
        System.out.println("demoAddMethod: " + m.invoke(a));
    }

}
