package org.practice.manual.lombok.features;

import org.junit.Test;

public class ToStringExampleTest {

    @Test
    public void test() {
        ToStringExample example = new ToStringExample();
        System.out.println(example.toString());
    }
}
