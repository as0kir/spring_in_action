package com.apress.prospring4.ch5.after;

import java.util.Random;

public class KeyGenerator {

    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;
    private Random rand = new Random();

    public long getKey() {
        int х = rand.nextInt(3);
        if (х == 1) {
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
