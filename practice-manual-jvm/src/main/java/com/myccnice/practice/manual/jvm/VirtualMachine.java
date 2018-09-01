package com.myccnice.practice.manual.jvm;

import java.nio.file.Path;
import java.util.Hashtable;


/**
 * 虚拟机
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public class VirtualMachine {

    /**
     * 初始类:包含 main 方法的类
     */
    private String initialClass;

    /**
     * 类加载器
     */
    private JvmClassLoader classLoader;

    /**
     * 方法区（Method Area）存储运行时类信息
     */
    private Hashtable<String, JvmClass> methodArea = new Hashtable<>();

    public VirtualMachine(Path classPath, String initialClass){
        classLoader = new JvmClassLoader(classPath);
        this.initialClass = initialClass;
    }

    /**
     * 执行虚拟机
     */
    public void run(String[] args) throws Exception {
        Env env = new Env(this);
        // 虚拟机执行时先找到包换main方法的类
        JvmClass clazz = findClass(initialClass);
        // 然后找到main方法
        JvmMethod method = clazz.getMethod("main", "([Ljava/lang/String;)V");
        // 执行入口方法
        method.call(env, clazz, (Object[]) args);
    }

    public JvmClass findClass(String className) throws ClassNotFoundException {
        JvmClass found = methodArea.get(className);
        if (found == null) {
            found = classLoader.loadClass(className);
            methodArea.put(className, found);
        }
        return found;
    }
}
