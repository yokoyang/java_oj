package designPattern.singleton;

public class LazyDoubleCheckSingleton {
    /**
     * 使用volatile进行修饰，禁止指令重排
     * 为什么需要对lazyDoubleCheckSingleton添加volatile修饰符
     * 因为lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();不是原子性的，分为三步：
     *
     * 为lazyDoubleCheckSingleton分配内存
     * 调用构造函数进行初始化
     * 将lazyDoubleCheckSingleton对象指向分配的内存【执行完这步lazyDoubleCheckSingleton才将不为null
     */
    private static volatile LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    /**
     * 构造函数私有化
     * */
    private LazyDoubleCheckSingleton() {

    }

    public static LazyDoubleCheckSingleton getLazyDoubleCheckSingleton() {
        if (lazyDoubleCheckSingleton == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazyDoubleCheckSingleton == null) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }

        return lazyDoubleCheckSingleton;
    }
}

