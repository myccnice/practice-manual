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

    public void setLocalVariables(Slots<Object> localVariables) {
        this.localVariables = localVariables;
    }

    public SlotsStack<Object> getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(SlotsStack<Object> operandStack) {
        this.operandStack = operandStack;
    }

    public Opcode[] getOpcodes() {
        return opcodes;
    }

    public void setOpcodes(Opcode[] opcodes) {
        this.opcodes = opcodes;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public Object getReturnVal() {
        return returnVal;
    }

    public void setReturnVal(Object returnVal) {
        this.returnVal = returnVal;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

}
