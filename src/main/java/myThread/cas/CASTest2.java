package myThread.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CASTest2 {
    //账户初始值为0
    private static AtomicInteger balance = new AtomicInteger(0);

    public static void main(String[] args) {
        //执行10000次转账，每次转入1元
        int count = 10000;
        //线程池
        final ExecutorService executorService = new ThreadPoolExecutor(3, 10, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        final long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            //cas test
//            executorService.submit(() -> transfer2(1));
            //faa test
            executorService.submit(() -> transfer3(1));
        }

        while (balance.get() < count) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        executorService.shutdown();
        final long end = System.currentTimeMillis();

        System.out.println(end - start);
        System.out.println(balance.get());
    }

    //转账服务 CAS
    private static void transfer2(int amount) {
        while (true) {
            int old = balance.get();
            int _new = old + amount;
            if (balance.compareAndSet(old, _new)) {
                break;
            }
        }

    }

    // FAA
    private static void transfer3(int amount) {
        balance.getAndAdd(amount);
    }
}
