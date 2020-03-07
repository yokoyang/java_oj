package Algorithm.threadRelated;

import java.util.concurrent.Semaphore;

public class NAlterNatePrint {
    //通过N个线程顺序循环打印从0至100，如给定N=3则输出:
    //thread0: 0
    //thread1: 1
    //thread2: 2
    //thread0: 3
    //thread1: 4
    //.....
    static int result = 0;

    public static void main(String[] args) throws InterruptedException {
        int N = 3;
        Thread[] threads = new Thread[N];
        final Semaphore[] syncObjects = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            syncObjects[i] = new Semaphore(1);
            if (i != 0) {
                syncObjects[i].acquire();
            }
        }
        for (int i = 0; i < N; i++) {
            final Semaphore cur = syncObjects[i];
            final Semaphore nextSemaphore = syncObjects[(i + 1) % N];
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            cur.acquire();
                            System.out.printf("thread_%d: %d\n", index, result++);
                            if (result > 100) {
                                System.exit(0);
                            }
                            nextSemaphore.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            threads[i].start();
        }

    }

}
