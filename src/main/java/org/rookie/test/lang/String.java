package org.rookie.test.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class String {
    public static void main(java.lang.String[] args) throws IOException {
/*        System.out.println(String.class.getClassLoader());
        java.lang.String str = "123";
        System.out.println(str.getClass().getClassLoader());
        ClassLoader cl = String.class.getClassLoader();
        while (cl!=null){
            System.out.println(cl);
            cl = cl.getParent();
        }*/

        List<Object> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            int[] a = new int[1024*1024];
            list.add(a);
        }
        System.in.read();
    }
}
