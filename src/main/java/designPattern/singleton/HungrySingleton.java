package designPattern.singleton;

public class HungrySingleton {
    private static HungrySingleton hungrySingleton = new HungrySingleton();

    /**
     * 私有构造函数，不能被外部所访问
     */
    private HungrySingleton() {}

    /**
     * 返回单例对象
     * */
    public static HungrySingleton getHungrySingleton() {
        return hungrySingleton;
    }
}

