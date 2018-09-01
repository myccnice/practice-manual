package com.myccnice.practice.manual.jvm;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Method;

/**
 * 字节码定义的 Java 类( 区别于 native 类 )
 *
 * create in 2018年9月1日
 * @author wangpeng
 */
public class JvmOpcodeClass implements JvmClass {

    private ClassFile classFile;
    private HashMap<String, JvmOpcodeMethod> methods = new HashMap<>();

    static public JvmOpcodeClass read(Path path) throws ClassNotFoundException {
        try {
            return  new JvmOpcodeClass(ClassFile.read(path));
        } catch (ConstantPoolException e) {
            throw new InternalError(e);
        } catch (IOException e) {
            throw new ClassNotFoundException(e.toString());
        }
    }

    private JvmOpcodeClass(ClassFile classFile) throws ConstantPoolException {
        this.classFile = classFile;
        for (Method method : classFile.methods) {
            String name = method.getName(classFile.constant_pool);
            String desc = method.descriptor.getValue(classFile.constant_pool);
            methods.put(name+":"+desc, new JvmOpcodeMethod(this.classFile, method));
        }
        // 准备阶段
        prepare();
        // 初始化阶段
        init();
    }

    /**
     * 准备阶段（Preparation）
     * 分配静态变量，并初始化为默认值，但不会执行任何字节码，在初始化阶段（init) 会有显式的初始化器来初始化这些静态字段，所以准备阶段不做这些事情。
     * @see `http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-5.html#jvms-5.4.2`
     */
    private void prepare(){

    }

    /**
     * 初始化阶段（Initialization）
     * 调用类的<clinit>方法（如果有）
     * @see `http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-5.html#jvms-5.5`
     */
    private void init(){

    }

    @Override
    public JvmMethod getMethod(String name, String desc) throws NoSuchMethodException {
        JvmMethod method = methods.get(name + ":" + desc);
        if (method == null) {
            throw new NoSuchMethodException();
        }
        return method;
    }

    @Override
    public Object getField(String name, String type, int flags) throws NoSuchFieldException, IllegalAccessException {
        throw new InternalError("Not Impl");
    }

}
