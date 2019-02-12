package org.practice.manual.lombok.features;

import org.junit.Test;

public class GetterSetterExampleTest {

    @Test
    public void test() {
        GetterSetterExample example = new GetterSetterExample();
        example.getAge();
    }

    @Test
    public void test2() {
        GetterSetterExample2 example2 = new GetterSetterExample2();
        example2.setAge(10);
        example2.setName("王鹏");
        // example2.getAge(); getAge方法没有
        GetterSetterExample e1 = example2.getExample();
        GetterSetterExample e2 = example2.getExample();
        System.out.println(e1 == e2);
    }
}