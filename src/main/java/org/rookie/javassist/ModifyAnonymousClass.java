package org.rookie.javassist;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.util.Arrays;

public class ModifyAnonymousClass {
    public ClassPool pool = ClassPool.getDefault();

    public void modifyAnonymousClass() {
        try {
            CtClass cc = pool.get("org.rookie.javassist.AnonymousClass");
            CtClass[] nestedClasses = cc.getNestedClasses();
            handle(nestedClasses);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handle(CtClass[] nested) {
        Arrays.stream(nested).forEach((e) -> {
            if (e.getName().equals("org.rookie.javassist.AnonymousClass$1")) {
                try {
                    CtMethod cm = e.getDeclaredMethod("run");
                    cm.instrument(new ExprEditor() {
                        @Override
                        public void edit(MethodCall m) throws CannotCompileException {
                            if (m.getClassName().equals("java.lang.String") &&
                                    m.getMethodName().equals("valueOf")) {
                                m.replace("{$1=\"Modify run()\";$_ = $proceed($$);}");
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
            if (e.getName().equals("org.rookie.javassist.AnonymousClass$2")) {
                try {
                    CtMethod cm = e.getDeclaredMethod("toString");
                    cm.instrument(new ExprEditor() {
                        @Override
                        public void edit(MethodCall m) throws CannotCompileException {
                            if (m.getClassName().equals("java.lang.String") &&
                                    m.getMethodName().equals("valueOf")) {
                                m.replace("{$1=\"Modify toString()\";$_ = $proceed($$);}");
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
        //Modify
        ModifyAnonymousClass mic = new ModifyAnonymousClass();
        mic.modifyAnonymousClass();
        //After
        AnonymousClass after = new AnonymousClass();
        after.hello();
    }
}
