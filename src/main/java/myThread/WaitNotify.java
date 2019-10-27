package myThread;

import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "wait");
        Thread notify = new Thread(new Notify(), "notify");
        waitThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {

        }
        notify.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait ");
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("flag false, running");
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock again");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object getRequest() {
        return null;
    }

    //    超时等待模式
    public synchronized Object timeGet(long mills) throws InterruptedException {
        long future = System.currentTimeMillis() + mills;
        long remaining = mills;
        Object res = null;
        res = getRequest();
        while (remaining > 0 && (res == null)) {
            wait(remaining);
            remaining = future - System.currentTimeMillis();
        }
        return res;
    }
}
