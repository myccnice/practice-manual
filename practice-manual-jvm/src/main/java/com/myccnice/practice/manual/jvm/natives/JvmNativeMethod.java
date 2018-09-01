package com.myccnice.practice.manual.jvm.natives;

import java.lang.reflect.Method;

import com.myccnice.practice.manual.jvm.Env;
import com.myccnice.practice.manual.jvm.JvmMethod;
import com.myccnice.practice.manual.jvm.StackFrame;

/**
 * 本地方法,在我们这里值JDK提供的方法
 *
 * create in 2018年9月1日
 * @author wangpeng
 */
public class JvmNativeMethod implements JvmMethod {

    private Method method;

    public JvmNativeMethod(Method method) {
        this.method = method;
    }

    @Override
    public void call(Env env, Object thiz, Object... args) throws Exception {
        // 方法调用就会创建一个新的栈帧
        StackFrame frame = env.getStack().newFrame();
        // 执行本地方法获取返回值
        Object res = method.invoke(thiz, args);
        // 将返回值推入调用者的操作数栈
        frame.setReturn(res, method.getReturnType().getName());
    }

    public Method getNativeMethod() {
        return method;
    }
}
