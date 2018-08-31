package com.myccnice.practice.manual.jvm;

import com.myccnice.practice.manual.jvm.opcodes.Opcode;

public class BytecodeInterpreter {

    //执行字节码
    public static void run(Env env) throws Exception {
        //只需要最外层调用执行栈上操作
        if(env.getStack().isRunning()){
            return;
        }
    }
    // TODO
    public static Opcode[] parseCodes(byte[] codes){
        return null;
    }
}
