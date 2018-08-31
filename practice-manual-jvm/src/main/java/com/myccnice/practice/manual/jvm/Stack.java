package com.myccnice.practice.manual.jvm;

import com.myccnice.practice.manual.jvm.opcodes.Opcode;
import com.sun.tools.classfile.ConstantPool;

/**
 * 虚拟机栈，每个线程持有一个独立的栈。
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public class Stack {

    /**
     * 默认栈容量
     */
    private static final int DEFAULT_CAPACITY = 1024;
    /**
     * 实际存储数据使用可指定曹位置大小的栈
     */
    private SlotsStack<StackFrame> frames = new SlotsStack<>(DEFAULT_CAPACITY);
    /**
     * 方法调用是否已经返回
     */
    private boolean running = false;

    /**
     * 方法调创建栈帧
     */
    public StackFrame newFrame() {
        StackFrame frame = new StackFrame(null, null, 0, 0);
        frames.push(frame);
        return frame;
    }

    public StackFrame newFrame(ConstantPool constantPool, Opcode[] opcodes, int variables, int stackSize) {
        StackFrame frame = new StackFrame(constantPool, opcodes, variables, stackSize);
        frames.push(frame, 1);
        return frame;
    }

    public SlotsStack<StackFrame> getFrames() {
        return frames;
    }

    public void setFrames(SlotsStack<StackFrame> frames) {
        this.frames = frames;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}
