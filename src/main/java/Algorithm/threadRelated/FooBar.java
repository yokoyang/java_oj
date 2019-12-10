package Algorithm.threadRelated;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    //    leetcode 1115
    //    输入: n = 2
    //输出: "foobarfoobar"
    //解释: "foobar" 将被输出两次。
    private int n;
    private ReentrantLock lock = new ReentrantLock();

    private Condition fooTurn = lock.newCondition();
    private Condition barTurn = lock.newCondition();
    private boolean isFooTurn = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while (!isFooTurn) {
                    fooTurn.await();
                }
                printFoo.run();
                isFooTurn = false;
                barTurn.signal();
            } finally {
                lock.unlock();
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                lock.lock();
                while (isFooTurn) {
                    barTurn.await();
                }
                printBar.run();
                isFooTurn = true;
                fooTurn.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    static class PrintFoo implements Runnable {

        @Override
        public void run() {
            System.out.println("foo");
        }
    }

    static class PrintBar implements Runnable {
        @Override
        public void run() {
            System.out.println("bar");
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(1);
        new Thread(() -> {
            try {
                fooBar.foo(new PrintFoo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                fooBar.bar(new PrintBar());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
