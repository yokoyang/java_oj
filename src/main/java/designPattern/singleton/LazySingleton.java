package designPattern.singleton;

public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    /**
     * 构造函数私有化
     * */
    private LazySingleton() {
    }

    private static LazySingleton getLazySingleton() {
        if (lazySingleton == null) {
            return new LazySingleton();
        }

        return lazySingleton;
    }
}
