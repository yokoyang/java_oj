package Algorithm.threadRelated;

public class AlternatingPrint {
    //    交替打印0到100
    static class SolutionTask implements Runnable {
        static int value = 0;

        @Override
        public void run() {
            while (value <= 10) {
                synchronized (SolutionTask.class) {
                    System.out.printf("%s :%d\n", Thread.currentThread().getName(), value++);
                    SolutionTask.class.notify();
                    try {
                        SolutionTask.class.wait();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new SolutionTask(), "偶数");
        Thread t2 = new Thread(new SolutionTask(), "奇数");
        t1.start();
        t2.start();
    }
}
