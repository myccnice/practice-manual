package org.practice.manual.tool;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeUtil {

    public static Unsafe getUnsafeInstance() throws Exception{
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe)field.get(Unsafe.class);
    }

    /**
     * 在jdk7中，String.intern不再拷贝string对象实例，而是保存第一次出现的对象的引用。
     * 在下面的代码中，通过Unsafe修改被引用对象s的私有属性value达到间接修改s1的效果！
     * @throws Exception 
     */
    public static void molestString(String molested) throws Exception {
        Unsafe u = getUnsafeInstance();
        //获取s的实例变量value
        Field valueInString = String.class.getDeclaredField("value");
        //获取value的变量偏移值
        long offset = u.objectFieldOffset(valueInString);
        // value本身是一个char[],要修改它元素的值，仍要获取baseOffset和indexScale
        long base = u.arrayBaseOffset(char[].class);
        long scale = u.arrayIndexScale(char[].class);
        //获取value
        char[] values = (char[]) u.getObject(molested, offset);
        // 'A' = 65 'a' = 97  差 32
        // 'Z' = 90 'z' = 122
        if (values != null) {
            for (int i = 0 ; i < values.length; i++) {
                char c = values[i];
                if (c >= 'a' && c <= 'z') {
                    u.putChar(values, base + scale * i, (char)(c - 32));
                }
            }
        }
    }
}
