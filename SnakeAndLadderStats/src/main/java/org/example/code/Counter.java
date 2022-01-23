package org.example.code;

class Counter {

    private Integer value = 0;

    public static Counter start(int startsAt) {
        Counter instace = new Counter();
        instace.value = startsAt;
        return instace;
    }

    public void count() {
        value += 1;
    }

    public int result() {
        return value.intValue();
    }
}