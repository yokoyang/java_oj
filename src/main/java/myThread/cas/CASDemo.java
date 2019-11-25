package myThread.cas;


import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        atomicInteger.getAndIncrement();

        boolean flag = atomicInteger.compareAndSet(4, 9);

        System.out.println(flag + " " + atomicInteger.get());
    }
}

