package asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestCompletableFuture {
    void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    static void runAsyncExample() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().isDaemon());
        });
    }

    static void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", cf.getNow(null));
    }

    static void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        System.out.println(cf.getNow(null));
        System.out.println(cf.join());
        assertEquals("MESSAGE", cf.join());
    }

    private static void transTwoState() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> {
            return s1 + " " + s2;
        }).join();
        System.out.println(result);

    }

    public static void main(String[] args) {
        thenApplyAsyncExample();
        thenApplyExample();
        runAsyncExample();
        String result = CompletableFuture.supplyAsync(() -> {
            return "Hello ";
        }).thenApplyAsync(v -> v + "world").join();
        System.out.println(result);
        CompletableFuture.supplyAsync(() -> {
            return "Hello ";
        }).thenAccept(v -> {
            System.out.println("consumer: " + v);
        });
        transTwoState();
        TestCompletableFuture testCompletableFuture = new TestCompletableFuture();
        testCompletableFuture.completedFutureExample();
    }
}
