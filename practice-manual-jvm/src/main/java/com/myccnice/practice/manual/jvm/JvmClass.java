package com.myccnice.practice.manual.jvm;

/**
 * 模拟类对象
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public interface JvmClass {

    /**
     * 获取方法实例
     * @param name 方法名，如`main`
     * @param desc 方法类型描述，如`([Ljava/lang/String;)V`
     * @return 方法对象
     * @throws NoSuchMethodException
     */
    public JvmMethod getMethod(String name, String desc) throws NoSuchMethodException;

    /**
     * 获取属性
     * @param name 属性名
     * @param type 属性类型
     * @return
     * @throws NoSuchFieldException
     */
    public Object getField(String name, String type) throws NoSuchFieldException, IllegalAccessException;
}
