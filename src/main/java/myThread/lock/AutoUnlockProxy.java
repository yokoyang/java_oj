package myThread.lock;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoUnlockProxy implements Closeable {

    private Lock lock;

    public AutoUnlockProxy(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void close() throws IOException {
        lock.unlock();
        System.out.println("释放锁");
    }

    public void lock() {
        lock.lock();
    }


    public void tryLock(long time, TimeUnit unit) throws InterruptedException {
        lock.tryLock(time, unit);
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        try (AutoUnlockProxy autoUnlockProxy = new AutoUnlockProxy(lock)) {
            autoUnlockProxy.lock();
            System.out.println("加锁了");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
