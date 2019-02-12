package org.practice.manual.lombok.features;

import org.junit.Test;

public class NonNullExampleTest {

    @Test
    public void test1() {
        new NonNullExample(null);
    }
}
