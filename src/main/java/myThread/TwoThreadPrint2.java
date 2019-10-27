package myThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TwoThreadPrint2 {
    public static AtomicInteger countAtomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        //打印字母
        Thread t1 = new Thread(() -> {
            int i = 0;
            char ch = 'A';
            while (true) {
                if (i >= 26) {
                    return;
                }
                if (countAtomicInteger.get() == 2) {
                    System.out.print((char) (ch + i));
                    countAtomicInteger.set(0);
                    i++;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            int i = 1;
            while (true) {
                if (i > 52) {
                    return;
                }
                if (countAtomicInteger.get() < 2) {
                    countAtomicInteger.incrementAndGet();
                    System.out.print(i);
                    i++;
                }
            }
        });
        t1.start();
        t2.start();
    }
}
