package com.myccnice.practice.manual.jvm;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 类加载器
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public class JvmClassLoader {

    /**
     * 类搜索路径
     */
    private Path classPath;

    public JvmClassLoader(Path classPath) {
        this.classPath = classPath;
    }

    public JvmClass loadClass(String className) throws ClassNotFoundException{
        // 在类路径下寻找class文件
        String fileName = classPath + "/" + className.replace(".", "/")+".class";
        Path path = Paths.get(fileName);
        // 如果文件存在，加载文件字节码
        // 否则尝试通过虚拟机宿主加载指定类，并将加载后的类当做 native 类
        if(Files.exists(path)){
            return null;
        }
        // TODO
        return null;
    }
}
