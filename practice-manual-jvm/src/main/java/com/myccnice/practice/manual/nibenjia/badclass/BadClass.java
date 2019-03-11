package com.myccnice.practice.manual.nibenjia.badclass;

public class BadClass {

    private static int a = 100;

    static {
        System.out.println("before init" + a);
        int b=3/0;
        System.out.println("after init" + b);
    }

    public static void doSomething(){
        System.out.println("do somthing");
    }

    public static void main(String args[]){
        try{
            BadClass.doSomething();
        }catch (Throwable e){
            e.printStackTrace();
        }
        BadClass.doSomething();
    }
}
