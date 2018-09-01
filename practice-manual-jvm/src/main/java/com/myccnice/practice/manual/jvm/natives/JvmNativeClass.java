package com.myccnice.practice.manual.jvm.natives;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import jdk.internal.org.objectweb.asm.Type;

import com.myccnice.practice.manual.jvm.JvmClass;
import com.myccnice.practice.manual.jvm.JvmMethod;

public class JvmNativeClass implements JvmClass {

    private Class<?> nativeClass;
    private Map<String, JvmNativeMethod> methods = new HashMap<>();
    
    public JvmNativeClass(Class<?> nativeClass){
        this.nativeClass = nativeClass;
        for (Method method : this.nativeClass.getMethods()) {
            String key = method.getName()+":"+Type.getMethodDescriptor(method);
            methods.put(key, new JvmNativeMethod(method));
        }
    }
    @Override
    public JvmMethod getMethod(String name, String desc) throws NoSuchMethodException {
        JvmNativeMethod method = methods.get(name + ":" + desc);
        if (method == null) {
            throw new NoSuchMethodException();
        }
        return method;
    }

    @Override
    public Object getField(String name, String type, int flags)
            throws NoSuchFieldException, IllegalAccessException {
        Field filed = nativeClass.getField(name);
        return filed.get(nativeClass);
    }

}
