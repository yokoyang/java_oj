package myThread.myThreadPool;

public interface ThreadPool<Job extends Runnable> {
    //执行一个job，这个job需要实现runnable
    void execute(Job job);

    //关闭线程池
    void shutdown();

    //添加工作线程
    void addWorkers(int num);

    //删除工作线程
    void removeWorker(int num);

    //得到正在等待执行的任务数量
    int getJobSize();
}
