package org.practice.manual.lombok.features;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class BuilderExample {

    private int age;

    @Builder.Default
    private String name = "a";

    @Singular
    private Set<String> students;
}
