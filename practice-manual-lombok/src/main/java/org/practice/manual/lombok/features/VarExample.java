package org.practice.manual.lombok.features;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.var;

/**
 * https://projectlombok.org/features/var
 * 
 * var和val基本类似，只是var不是final的
 *
 * @author 王鹏
 * @date 2019年1月31日
 */
public class VarExample {

    public String example() {
        var example = new ArrayList<String>();
        example.add("Hello, World!");
        var foo = "var";
        foo = example.get(0);
        return foo.toLowerCase();
    }

    public void example2() {
        var map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        map.entrySet().stream().forEach(
            t -> System.out.printf("%d: %s\n", t.getKey(), t.getValue())
        );
    }
}
