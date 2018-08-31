package com.myccnice.practice.manual.jvm;

/**
 * 运行环境
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public class Env {

    /**
     * 虚拟机栈
     */
    private Stack stack = new Stack();

    /**
     * 当前虚拟机
     */
    private VirtualMachine vm;

    public Env(VirtualMachine vm){
        this.vm = vm;
    }

    public Stack getStack() {
        return stack;
    }

    public VirtualMachine getVm() {
        return vm;
    }

}
