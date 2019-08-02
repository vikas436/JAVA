package com.JavaLearn.multithreading;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class OddEvenNum {

    public static void main(String arsg[]) throws InterruptedException {
        final OddEven oddEven = new OddEven();
        Thread produce = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oddEven.even();
                } catch (Exception e) {
                    System.out.println("Error "+e);
                }
            }
        });

        Thread consume = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oddEven.odd();
                } catch (Exception e) {
                    System.out.println("Error "+e);
                }
            }
        });
        produce.start();
        consume.start();
        produce.join();
        consume.join();
    }
}

class OddEven {
    LinkedList<Integer> buffer = new LinkedList<>();
    OddEven() {
        Random rand = new Random();
        for(int i=0;i<10;i++) {
            buffer.add(rand.nextInt(50));
        }
    }
    int ind = 0;
    public void even() throws InterruptedException {
        int data = -1;
        while(true) {
            synchronized (this) {
                if(ind<buffer.size() && buffer.get(ind)%2==1)
                    wait();
                if(ind==buffer.size()) break;
                if(buffer.get(ind)%2==0) {
                    data = buffer.get(ind);
                    ind++;
                    System.out.println("Even data:  " + data);
                    notify();
                }
                sleep(5);
            }
            // break condition
            if(ind==buffer.size()) break;
        }
    }

    public void odd() throws InterruptedException {
        int val = -1;
        while (true) {
            synchronized (this) {
                if(ind<buffer.size() && buffer.get(ind)%2==0)
                    wait();
                if(ind==buffer.size()) break;
                if(buffer.get(ind)%2==1) {
                    val = buffer.get(ind);
                    ind++;
                    System.out.println("Odd data:  " + val);
                    notify();
                }
                sleep(5);
            }
            // break condition
            if(ind==buffer.size()) break;
        }
    }

}
