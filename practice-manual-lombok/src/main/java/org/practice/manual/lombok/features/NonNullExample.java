package org.practice.manual.lombok.features;

import lombok.NonNull;

/**
 * https://projectlombok.org/features/NonNull
 *
 * @author 王鹏
 * @date 2019年1月31日
 */
public class NonNullExample {

    private String name;

    public NonNullExample(@NonNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
