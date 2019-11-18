package designPattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectAttackTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //静态内部类
        LazyStaticSingleton lazyStaticSingleton = LazyStaticSingleton.getLazyStaticSingleton();
        //通过反射创建LazyStaticSingleton
        Constructor<LazyStaticSingleton> constructor = LazyStaticSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyStaticSingleton lazyStaticSingleton1 = constructor.newInstance();
        //打印结果为false，说明又创建了一个新对象
        System.out.println(lazyStaticSingleton == lazyStaticSingleton1);

        //双重锁检查
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton = LazyDoubleCheckSingleton.getLazyDoubleCheckSingleton();
        Constructor<LazyDoubleCheckSingleton> lazyDoubleCheckSingletonConstructor = LazyDoubleCheckSingleton.class.getDeclaredConstructor();
        lazyDoubleCheckSingletonConstructor.setAccessible(true);
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton1 = lazyDoubleCheckSingletonConstructor.newInstance();
        System.out.println(lazyDoubleCheckSingleton == lazyDoubleCheckSingleton1);
    }
}


