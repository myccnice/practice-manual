package com.myccnice.practice.manual.concurrent.util;

import java.util.Random;

public abstract class PseudoRandom {

    protected int calculateNext(int seed) {
        Random r = new Random(seed);
        return r.nextInt();
    }

    abstract int nextInt();
}
