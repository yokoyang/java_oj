package Algorithm.threadRelated;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOdd2 {
    public static void main(String[] args) {
        final ZeroEvenOdd2 zeroEvenOdd2 = new ZeroEvenOdd2(10);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd2.zero();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd2.odd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd2.even();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    private int n;

    public ZeroEvenOdd2(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock();
    Condition z = lock.newCondition();
    Condition num = lock.newCondition();
    volatile boolean zTurn = true;
    int zIndex = 0;

    public void zero() throws InterruptedException {
        for (; zIndex < n; ) {
            lock.lock();
            try {
                while (!zTurn) {
                    z.await();
                }
                System.out.println(0);
                zTurn = false;
                num.signalAll();
                zIndex++;
            } finally {
                lock.unlock();
            }
        }
    }

    public void even() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {
                while (zTurn || (zIndex & 1) == 1) {
                    num.await();
                }
                System.out.println(i);

                zTurn = true;
                z.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                while (zTurn || (zIndex & 1) == 0) {
                    num.await();
                }
                System.out.println(i);
                zTurn = true;
                z.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}

