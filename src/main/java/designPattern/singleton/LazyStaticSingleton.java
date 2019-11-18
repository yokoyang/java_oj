package designPattern.singleton;

public class LazyStaticSingleton {
    /**
     * 静态内部类
     */
    private static class LazyStaticSingletonHolder {
        private static LazyStaticSingleton lazyStaticSingleton = new LazyStaticSingleton();
    }

    /**
     * 构造函数私有化
     */
    private LazyStaticSingleton() {
    }

    public static LazyStaticSingleton getLazyStaticSingleton() {
        return LazyStaticSingletonHolder.lazyStaticSingleton;
    }
}

