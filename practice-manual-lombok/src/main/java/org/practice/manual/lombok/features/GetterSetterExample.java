package org.practice.manual.lombok.features;

import org.practice.manual.lombok.annotain.Nothing;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class GetterSetterExample {

    @Getter
    @Setter(onMethod_={@Nothing}, onParam_= @NonNull)
    private Integer age = 10;

    @Setter(AccessLevel.PROTECTED)
    private String name;

    @Override
    public String toString() {
        return String.format("%s (age: %d)", name, age);
    }
}
