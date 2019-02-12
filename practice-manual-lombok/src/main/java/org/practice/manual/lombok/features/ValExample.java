package org.practice.manual.lombok.features;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.val;

/**
 * https://projectlombok.org/features/val
 * val：在声明本地变量时，可以使用val来代替其真实的类型，会在编译期进行类型推导，并且这个变量会被声明为final类型，必须赋初始值。
 * 主要使用在本地变量和循环中，不能在属性中使用。
 *
 * @author 王鹏
 * @date 2019年1月31日
 */
public class ValExample {

    public String example() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }

    public void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        map.entrySet().stream().forEach(
            t -> System.out.printf("%d: %s\n", t.getKey(), t.getValue())
        );
    }
}
