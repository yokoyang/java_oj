package myThread.lock;

class DeadLock {
    public static void main(String[] args) {
        Object l1 = new Object(), l2 = new Object();
        Tr1 tr1=new Tr1(l1, l2);
        Tr2 tr2=new Tr2(l1, l2);

        Thread t1=new Thread(tr1);
        Thread t2=new Thread(tr2);
        t1.start();
        t2.start();
    }
}

class Tr1 implements Runnable {

    Object lock;
    Object lock2;

    public Tr1(Object lock, Object lock2) {
        this.lock = lock;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        //获取lock
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "获取了lock锁");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            //获取lock2
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获取了lock2锁");
            }
        }

    }
}


class Tr2 implements Runnable {

    Object lock;
    Object lock2;

    public Tr2(Object lock, Object lock2) {
        this.lock = lock;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        //获取lock2
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + "获取了lock2锁");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            //获取lock
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "获取了lock锁");
            }
        }

    }
}

