package com.myccnice.practice.manual.jvm;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 通过这个类来启动我们的虚拟机。必须传一个方法就是加载的类名
 *
 * create in 2018年9月1日
 * @author wangpeng
 */
public class JJvm {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("usage: JJvm class [args...]");
            return;
        }
        String[] paremates = Arrays.copyOfRange(args, 1, args.length);
        VirtualMachine vm = new VirtualMachine(Paths.get("."), args[0]);
        try {
            vm.run(paremates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
