package org.rookie.javassist;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.Arrays;

public class ModifyInnerClass {
    public ClassPool pool = ClassPool.getDefault();

    public void modifyPublicClass() {
        try {
            CtClass cc = pool.get("org.rookie.javassist.InnerClass");
            CtClass[] nestedClasses = cc.getNestedClasses();
            handle(nestedClasses);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handle(CtClass[] nested) {
        Arrays.stream(nested).forEach((e) -> {
            if (e.getName().equals("org.rookie.javassist.InnerClass$PublicClass")) {
                try {
                    CtMethod cm = e.getDeclaredMethod("hello");
                    cm.instrument(new ExprEditor() {
                        @Override
                        public void edit(MethodCall m) throws CannotCompileException {
                            if (m.getClassName().equals("java.lang.String") &&
                                    m.getMethodName().equals("valueOf")) {
                                m.replace("{$1=\"Modify Public Class hello() Method\";$_ = $proceed($$);}");
                            }
                        }
                    });
                    e.toClass(InnerClass.class.getClassLoader());
                } catch (NotFoundException e1) {
                    e1.printStackTrace();
                } catch (CannotCompileException e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getName().equals("org.rookie.javassist.InnerClass$PrivateClass")) {
                try {
                    CtMethod cm = e.getDeclaredMethod("hello");
                    cm.instrument(new ExprEditor() {
                        @Override
                        public void edit(MethodCall m) throws CannotCompileException {
                            if (m.getClassName().equals("java.lang.String") &&
                                    m.getMethodName().equals("valueOf")) {
                                m.replace("{$1=\"Modify Private Class hello() Method\";$_ = $proceed($$);}");
                            }
                        }
                    });
                    e.toClass(InnerClass.class.getClassLoader());
                } catch (NotFoundException e1) {
                    e1.printStackTrace();
                } catch (CannotCompileException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
/*        InnerClass before = new InnerClass();
        before.publicClassHello();
        before.privateClassHello();*/
        //Modify
        ModifyInnerClass mic = new ModifyInnerClass();
        mic.modifyPublicClass();
        //After
        InnerClass after = new InnerClass();
        after.publicClassHello();
        after.privateClassHello();
    }
}
