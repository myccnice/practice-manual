package org.practice.manual.lombok.features;

import org.junit.Test;

public class BuilderExampleTest {

    @Test
    public void test() {
        BuilderExample build = BuilderExample.builder().age(10).name("wz").student("a").build();
        System.out.println(build);
    }
}
