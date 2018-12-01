package com.malk.citer.exception;

class Counter {

    private static int count = 0;

    static int getCount() {
        count++;
        return count;
    }
}
