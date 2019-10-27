package myThread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TwoThreadPrint3 {

    static Lock lock = new ReentrantLock();
    static Condition letterCondition = lock.newCondition();
    static Condition numCondition = lock.newCondition();
    static boolean flag = true;

    /**
     * @param args
     */
    public static void main(String[] args) {

        new Thread(() -> {
            char ch = 'A';
            int i = 0;
            while (i < 26) {
                lock.lock();
                try {
                    if (flag) {
                        letterCondition.await();
                    }
                    System.out.print((char) (ch + i));
                    i++;
                    flag = true;
                    numCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        //打印数字的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (i < 53) {
                    lock.lock();
                    try {
                        if (!flag) {
                            numCondition.await();
                        }
                        System.out.print(i + "" + (i + 1));
                        i += 2;
                        flag = false;
                        letterCondition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();

    }

}
