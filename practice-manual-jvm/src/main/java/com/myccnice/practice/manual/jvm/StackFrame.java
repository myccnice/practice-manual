package com.myccnice.practice.manual.jvm;

import com.myccnice.practice.manual.jvm.opcodes.Opcode;
import com.sun.tools.classfile.ConstantPool;

/**
 * 栈帧，对应 JVM 规范中的栈帧的概念，用于表示一次方法调用的上下文
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public class StackFrame {

    /**
     * 局部变量表(Local Variables）
     * 用于存储方法的局部变量
     */
    private Slots<Object> localVariables;

    /**
     * 操作数栈(Operand Stack）
     * 用于存储操作指令的输入输出
     */
    private SlotsStack<Object> operandStack;

    /**
     * 字节码
     */
    private Opcode[] opcodes;

    /**
     * 程序计数器
     */
    private int pc=0;

    /**
     * 常量池（Constant Pool）
     */
    private ConstantPool constantPool;

    /**
     * 返回值
     */
    private Object returnVal;

    /**
     * 返回值类型
     */
    private String returnType;

    private boolean isReturned = false;

    public StackFrame(ConstantPool constantPool, Opcode[] opcodes, int variables, int stackSize) {
        this.constantPool = constantPool;
        this.opcodes = opcodes;
        this.operandStack = new SlotsStack<>(stackSize);
        this.localVariables = new Slots<>(variables);
    }
    

    public Slots<Object> getLocalVariables() {
        return localVariables;
    }

    public SlotsStack<Object> getOperandStack() {
        return operandStack;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public void setReturn(Object returnVal, String returnType) {
        this.isReturned = true;
        this.returnVal = returnVal;
        this.returnType = returnType;
    }

    public Object getReturn() {
        return returnVal;
    }
    public String getReturnType() {
        return returnType;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public int getPC() {
        return pc;
    }
    public int increasePC(){
        return pc++;
    }
    public Opcode[] getOpcodes() {
        return opcodes;
    }
}
