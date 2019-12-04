package myThread.testLinkedBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestConsumerProducer {

    public static void main(String[] args) {

        final BlockingQueue<Apple> queue = new LinkedBlockingDeque<>(3);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();

    }
}

class Producer implements Runnable {
    BlockingQueue<Apple> queue;
    int appleId = 0;

    public Producer(BlockingQueue<Apple> queue) {
        this.queue = queue;
    }

    public boolean put(Apple apple) {
        return queue.offer(apple);
    }

    @Override
    public void run() {
        for (; ; ) {
            //                每间隔1s尝试生产
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产一个商品 " + appleId);
            put(new Apple(appleId++));
        }
    }
}

class Consumer implements Runnable {
    BlockingQueue<Apple> queue;

    public Consumer(BlockingQueue<Apple> queue) {
        this.queue = queue;
    }

    public Apple take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                System.out.println(take().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}