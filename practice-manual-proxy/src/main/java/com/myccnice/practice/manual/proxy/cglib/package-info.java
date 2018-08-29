/**
 * JDK的动态代理机制只能代理实现了接口的类，而没有实现接口的类就不能实现JDK的动态代理。
 * cglib是针对类来实现代理的，采用了非常底层的字节码技术，其原理是通过asm为一个类创建子类，
 * 并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。
 * 但因为采用的是继承，所以不能对final修饰的类进行代理。 
 *
 * create in 2017年9月8日
 * @author wangpeng
 */
package com.myccnice.practice.manual.proxy.cglib;