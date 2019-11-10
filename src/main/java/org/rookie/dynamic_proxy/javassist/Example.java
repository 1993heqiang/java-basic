package org.rookie.dynamic_proxy.javassist;

import javassist.*;

import java.util.Arrays;

public class Example {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.get("org.rookie.dynamic_proxy.javassist.Test");
        try {
            cc.getDeclaredMethod("g");
            System.out.println("g() is already defined in sample.Test.");
        } catch (NotFoundException e) {
            /* getDeclaredMethod() throws an exception if g()
             * is not defined in sample.Test.
             */
            CtMethod fMethod = cc.getDeclaredMethod("f");
            CtMethod gMethod = CtNewMethod.copy(fMethod, "g", cc, null);
            cc.addMethod(gMethod);
            cc.writeFile();    // update the class file
            System.out.println("g() was added.");
        }
        cc.toClass();
        Arrays.stream(Test.class.getDeclaredMethods()).forEach(method -> {
            System.out.println(method.getName());
        });
    }
}
