package myThread.myThreadExacuteor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
        String dirName = "E:\\project\\oj\\doc\\网易2019";
        String pattern = ".md";
        String regex = "表示";
        List<String> resultStringList = new ArrayList<>();
        File path = new File(dirName);
        if (path.exists() && path.isDirectory()) {
            for (File file : path.listFiles()) {
                if (file.getName().endsWith(pattern)) {
                    MyTask task = new MyTask(file, regex);
                    Future<List<String>> result = executor.submit(task);
                    try {
                        resultStringList.addAll(result.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        executor.shutdown();
        System.out.println(resultStringList);
        System.out.println(resultStringList.size());
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Callable<List<String>> {
        private String name;
        private File file;
        private String regex;

        public MyTask(String name) {
            this.name = name;
        }

         MyTask(File file, String regex) {
            this.file = file;
            this.regex = regex;
        }
//        @Override
//        public void run() {
//            try {
//                System.out.println(this.toString() + " is running!");
//                Thread.sleep(3000); //让任务执行慢点
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }

        @Override
        public List<String> call() throws Exception {
            List<String> callList = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains(regex)) {
                    callList.add(str);
                }
            }
            return callList;
        }
    }
}