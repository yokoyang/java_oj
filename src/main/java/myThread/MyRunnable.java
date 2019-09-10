package myThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("a");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyRunnable instance = new MyRunnable();
        Thread t = new Thread(instance);
        t.start();
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Future<Integer> result = executorService.submit(new MyCallable());
            executorService.submit(new MyRunnable());
            integerList.add(result.get());
        }
        executorService.shutdown();
        for (int i = 0; i < integerList.size(); i++) {
            System.out.println(integerList.get(i));
        }
    }


    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 12;
        }
    }
}
