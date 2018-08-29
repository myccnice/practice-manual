package com.myccnice.practice.manual.asm;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

public class ReflectASMClient {

    private static Map<Class<?>, MethodAccess> cache = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // testJdkReflect(); //    65354
        // testReflectAsm(); // 274967,2278
        SomeClass someObject = new SomeClass();
        someObject.setName("张三");
        SomeInnerClass sic = new SomeInnerClass();
        sic.setName("李四");
        someObject.setSic(sic);
        System.out.println(testAsmMethod(someObject, "sic.name"));
    }

    public static void testJdkReflect() throws Exception {
        SomeClass someObject = new SomeClass();
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
                Method method = SomeClass.class.getMethod("setName", String.class);
                method.invoke(someObject, "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin + " ");
        }
    }

    public static void testReflectAsm() {
        SomeClass someObject = new SomeClass();
        MethodAccess access = MethodAccess.get(SomeClass.class);
        for (int i = 0; i < 5; i++) {
            long begin = System.currentTimeMillis();
            for (int j = 0; j < 100000000; j++) {
                access.invoke(someObject, "setName", "Unmi");
            }
            System.out.print(System.currentTimeMillis() - begin + " ");
        }
    }

    public static <T> void testAsmField(T instance, String fieldName) {
        FieldAccess access = FieldAccess.get(instance.getClass());
        System.out.println(access.get(instance, fieldName));
    }

    public static Object testAsmMethod(Object instance, String fieldName) {
        if (instance == null || fieldName == null) {
            return null;
        }
        String[] fields = fieldName.split("\\.");
        for (int i = 0; i < fields.length && instance != null; i++) {
            instance = testAsmMethodSingle(instance, fields[i]);
        }
        return instance;
    }

    public static <T> Object testAsmMethodSingle(T instance, String fieldName) {
        if (instance == null || fieldName == null) {
            return null;
        }
        String methodName = getGetMethodName(fieldName);
        MethodAccess access = getMethodAccess(instance.getClass());
        return access.invoke(instance, methodName);
    }

    private static String getGetMethodName(String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        return sb.toString();
    }

    public static FieldChain buildFieldChain(String fieldChain) {
        String[] fieldArr = fieldChain.split(".");
        FieldChain root = new FieldChain();
        for (String field : fieldArr) {
            root.setField(field);
        }
        return root;
    }

    private static MethodAccess getMethodAccess(Class<?> type) {
        MethodAccess ma = cache.get(type);
        if (ma == null) {
            synchronized (cache) {
                ma = cache.get(type);
                if (ma == null) {
                    ma = MethodAccess.get(type);
                    cache.put(type, ma);
                }
            }
        }
        return ma;
    }
}
