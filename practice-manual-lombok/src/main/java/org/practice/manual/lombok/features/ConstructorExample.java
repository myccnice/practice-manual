package org.practice.manual.lombok.features;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(force=true)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ConstructorExample<T> {

    public static final String VERSION = "1.0";

    @Setter
    @Getter
    private int x;

    @Setter
    @Getter
    private int y = 1;

    @Getter
    @NonNull
    private T description;

    @Getter
    private static int a;

    @NoArgsConstructor
    public static class NoArgsExample {

        @NonNull
        private String field;
    }

}
