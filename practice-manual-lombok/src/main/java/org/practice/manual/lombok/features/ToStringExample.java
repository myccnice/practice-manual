package org.practice.manual.lombok.features;

import lombok.ToString;

/**
 * https://projectlombok.org/features/ToString
 *
 * @author 王鹏
 * @date 2019年1月31日
 */
@ToString
public class ToStringExample {

    public static final int STATIC_VAR = 10;

    private String name;

    private Square shape = new Square(5, 10);

    private String[] tags;

    @ToString.Exclude
    private int id;

    public String getName() {
        return this.name;
    }

    @ToString(includeFieldNames=true)
    public static class Square {
        private final int width, height;
        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
