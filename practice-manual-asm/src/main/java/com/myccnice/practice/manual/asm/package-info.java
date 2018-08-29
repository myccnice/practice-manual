/**
 * 一、什么是ASM
　* ASM是一个java字节码操纵框架，它能被用来动态生成类或者增强既有类的功能。
 * ASM 可以直接产生二进制 class 文件，也可以在类被加载入 Java 虚拟机之前动态改变类行为。
 * Java class 被存储在严格格式定义的 .class文件里，
 * 这些类文件拥有足够的元数据来解析类中的所有元素：类名称、方法、属性以及 Java 字节码（指令）。
 * ASM从类文件中读入信息后，能够改变类行为，分析类信息，甚至能够根据用户要求生成新类。
 * 二、如何使用ASM
　* ASM框架中的核心类有以下几个：
　* ①  ClassReader:该类用来解析编译过的class字节码文件。
　* ②  ClassWriter:该类用来重新构建编译后的类，比如说修改类名、属性以及方法，甚至可以生成新的类的字节码文件。
　* ③  ClassAdapter:该类也实现了ClassVisitor接口，它将对它的方法调用委托给另一个ClassVisitor对象。
 *
 * create in 2017年9月13日
 * @author wangpeng
 */
package com.myccnice.practice.manual.asm;