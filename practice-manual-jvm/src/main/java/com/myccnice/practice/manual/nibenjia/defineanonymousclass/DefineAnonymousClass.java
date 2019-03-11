package com.myccnice.practice.manual.nibenjia.defineanonymousclass;

import java.io.FileInputStream;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * https://mp.weixin.qq.com/s?__biz=MzIzNjI1ODc2OA==&mid=2650887385&idx=1&sn=53127fa81f6c6b513ce41b2c1f5e3669&chksm=f32f6066c458e9707f46dad3ec9c1aecd7b22e84d59f9799ccda5a0e1c60020e5a37fb389da2&scene=0&xtrack=1&key=47702ccb5c1d64c4170cc5e71f9f5452a06fa887e6041bbd5765fe83783d4326b8405b79c7f6a00848939e30b1c244522e115753ae4135493730fd8bf6f35cbe1f1427bacf45507a57a57d04e6ca1b44&ascene=1&uin=MjA3MDE0MjE4NQ%3D%3D&devicetype=Windows+7&version=62060728&lang=zh_CN&pass_ticket=Kpy39X748Csb%2BvzP7xDRc8wpQeXrqRRr7VThseZL1qcBlHfNGIlOZtVDduNDi8IS
 *
 * @author 王鹏
 * @date 2019年3月7日
 */
public class DefineAnonymousClass {

    public static void main(String args[]) throws Throwable {
        String filePath = "E:/nibenjia/HelloWorld.class";
        byte[] buffer = getFileContent(filePath);
        Unsafe unsafe = getUnsafe();
        Class<?> c1 = unsafe.defineAnonymousClass(UnsafeTest.class, buffer, null);
        System.out.println(c1.getName());
        Class<?> c2 = unsafe.defineAnonymousClass(UnsafeTest.class, buffer, null);
        System.out.println(c1 == c2);
        System.out.println(c2.getName());
    }

    private static Unsafe getUnsafe() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }

    private static byte[] getFileContent(String filePath) {
        byte[] buffer = new byte[411];
        try(FileInputStream fis = new FileInputStream(filePath)) {
            fis.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
