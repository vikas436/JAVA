package com.JavaLearn.multithreading;

import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class ProducerConsumer {

    public static void main(String arsg[]) throws InterruptedException {
        final ConsumeProduce comsumeProdece = new ConsumeProduce();
        Thread produce = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    comsumeProdece.produce();
                } catch (Exception e) {
                    System.out.println("Error "+e);
                }
            }
        });

        Thread consume = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    comsumeProdece.consume();
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

class ConsumeProduce {
    LinkedList<Integer> buffer = new LinkedList<>();
    int capacity = 2;
    int cnt = 0;
    public void produce() throws InterruptedException {
        int data = 0;
        while(true) {
            synchronized (this) {
                if(buffer.size()==capacity)
                    wait();
                buffer.add(data);
                System.out.println("Produce data:- " + data++);
                notify();
                sleep(500);
            }
            // break condition
            if(cnt++>100) break;
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if(buffer.size()==0)
                    wait();
                int val = buffer.removeFirst();
                System.out.println("Consume data:- " + val);
                notify();
                sleep(500);
            }
            // break condition
            if(cnt++>100+capacity) break;
        }
    }

}
