package org.practice.manual.lombok.features;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetterSetterExample2 {

    @Getter(AccessLevel.NONE)
    private int age;

    private String name;

    private GetterSetterExample example;
}
