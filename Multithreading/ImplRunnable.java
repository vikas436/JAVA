package com.JavaLearn.multithreading;

public class ImplRunnable {
    private static final int N = 5;
    public static void main(String args[]) {

        for(int i=0;i<N;i++) {
            Thread thread = new Thread(new MultiTreadingRunnable());
            thread.start();
        }
    }
}

class MultiTreadingRunnable implements Runnable {
    public void run() {
        System.out.println("Running thread... "+Thread.currentThread().getId());
        System.out.println("Thread status... "+Thread.currentThread().getState());
    }
}
