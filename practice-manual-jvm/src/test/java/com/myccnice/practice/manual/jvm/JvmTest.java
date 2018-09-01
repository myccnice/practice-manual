package com.myccnice.practice.manual.jvm;

import java.nio.file.Paths;

import org.junit.Test;

/**
 * JJvm测试。
 * 准备工作：在classPath下编写一个简单的Hellworld.java并编译成class文件。
 *
 * create in 2018年9月1日
 * @author wangpeng
 */
public class JvmTest {

    @Test
    public void test() {
        String classPath = "E:/jjvm";
        String className = "Helloworld";
        VirtualMachine vm = new VirtualMachine(Paths.get(classPath), className);
        try {
            vm.run(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
