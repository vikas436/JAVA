package com.JavaLearn.multithreading;

public class ExtendThread {
    private static final int N = 10;
    public static void main(String args[]) {

        for(int i=0;i<N;i++) {
            MultiTreadingTest multiTreadingTest = new MultiTreadingTest();
            multiTreadingTest.start();
        }

    }
}

class MultiTreadingTest extends Thread {
    public void run() {
        System.out.println("Running thread... "+Thread.currentThread().getId());
    }
}
