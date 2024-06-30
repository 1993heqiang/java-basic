package com.example.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.management.VMManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ManagementFactoryTests {

    @Test
    void runtimeMXBeanTest() throws Exception {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Assertions.assertNotNull(runtimeMXBean);
        Field jvmField = runtimeMXBean.getClass().getDeclaredField("jvm");
        jvmField.setAccessible(true);
        VMManagement vmManagement = (VMManagement) jvmField.get(runtimeMXBean);
        Method getProcessIdMethod = vmManagement.getClass().getDeclaredMethod("getProcessId");
        Assertions.assertNotNull(getProcessIdMethod);
        getProcessIdMethod.setAccessible(true);
        Object processId = getProcessIdMethod.invoke(vmManagement);
        Assertions.assertTrue(processId instanceof Integer);
    }
}
