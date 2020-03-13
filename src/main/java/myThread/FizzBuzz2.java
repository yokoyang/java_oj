package myThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class FizzBuzz2 {

    //    使用Lock解决
    public static void main(String[] args) throws InterruptedException {
        FizzBuzz2 fizzBuzz = new FizzBuzz2(16);
        Runnable rFizz = () -> System.out.println("fizz");
        Runnable rBuzz = () -> System.out.println("buzz");
        Runnable rFizzBuzz = () -> System.out.println("fizzbuzz");
        IntConsumer pNumber = x -> System.out.println(x);

        Runnable rFBFizz = () -> {
            try {
                fizzBuzz.fizz(rFizz);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        };
        Runnable rFBBuzz = () -> {
            try {
                fizzBuzz.buzz(rBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable rFBFizzBuzz = () -> {
            try {
                fizzBuzz.fizzbuzz(rFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable rNumber = () -> {
            try {
                fizzBuzz.number(pNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t1 = new Thread(rFBFizz);
        Thread t2 = new Thread(rFBBuzz);
        Thread t3 = new Thread(rFBFizzBuzz);
        Thread t4 = new Thread(rNumber);
        t4.start();
        t1.start();
        t2.start();
        t3.start();

    }

    private int n;
    ReentrantLock lock = new ReentrantLock();
    Condition fCond = lock.newCondition();
    Condition bCond = lock.newCondition();
    Condition fbCond = lock.newCondition();
    Condition nCond = lock.newCondition();
    volatile Boolean isPrintChar = false;

    public FizzBuzz2(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                lock.lock();
                try {
                    if (i % 5 != 0) {
                        while (!isPrintChar) {
                            fCond.await();
                        }
                        printFizz.run();
                        isPrintChar = false;
                        nCond.signal();
                    }
                } finally {
                    lock.unlock();
                }
            }

        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i = i + 5) {
            lock.lock();
            try {
                if (i % 3 != 0) {
                    while (!isPrintChar) {
                        bCond.await();
                    }
                    printBuzz.run();
                    isPrintChar = false;
                    nCond.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i = i + 15) {
            lock.lock();
            try {
                while (!isPrintChar) {
                    fbCond.await();
                }
                printFizzBuzz.run();
                isPrintChar = false;
                nCond.signal();
            } finally {
                lock.unlock();
            }

        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            try {
                while (isPrintChar) {
                    nCond.await();
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    fbCond.signal();
                    isPrintChar = true;
                } else if (i % 3 == 0) {
                    fCond.signal();
                    isPrintChar = true;
                } else if (i % 5 == 0) {
                    bCond.signal();
                    isPrintChar = true;
                } else {
                    printNumber.accept(i);
                    nCond.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
