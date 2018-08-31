package com.myccnice.practice.manual.jvm.opcodes;

import com.myccnice.practice.manual.jvm.Env;
import com.myccnice.practice.manual.jvm.StackFrame;

@FunctionalInterface
public interface Opcode {

    public void call(Env env, StackFrame frame) throws Exception;
}
