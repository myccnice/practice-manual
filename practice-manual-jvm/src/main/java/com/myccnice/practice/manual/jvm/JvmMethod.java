package com.myccnice.practice.manual.jvm;

/**
 * 模拟方法对象
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public interface JvmMethod {

    /**
     * 执行对象或者类方法
     * 方法被调用时，会产生一个新的栈帧，并推入当前线程的栈
     * 方法执行结束后，栈帧被退出，同时期返回值被推入上一个栈帧（当前方法的调用者）的操作数栈
     */
    public void call(Env env, Object thiz, Object ...args) throws Exception ;
}
