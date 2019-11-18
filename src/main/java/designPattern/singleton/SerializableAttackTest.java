package designPattern.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class SerializableAttackTest {
    public static void main(String[] args) {
        //懒汉式
        HungrySingleton hungrySingleton = HungrySingleton.getHungrySingleton();
        //序列化
        byte[] serialize = SerializationUtils.serialize((Serializable) hungrySingleton);
        //反序列化
        HungrySingleton hungrySingleton1 = (HungrySingleton) SerializationUtils.deserialize(serialize);
        System.out.println(hungrySingleton == hungrySingleton1);

        //双重锁
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton = LazyDoubleCheckSingleton.getLazyDoubleCheckSingleton();
        byte[] serialize1 = SerializationUtils.serialize((Serializable) lazyDoubleCheckSingleton);
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton11 = (LazyDoubleCheckSingleton) SerializationUtils.deserialize(serialize1);
        System.out.println(lazyDoubleCheckSingleton == lazyDoubleCheckSingleton11);


        //静态内部类
        LazyStaticSingleton lazyStaticSingleton = LazyStaticSingleton.getLazyStaticSingleton();
        byte[] serialize4 = SerializationUtils.serialize((Serializable) lazyStaticSingleton);
        LazyStaticSingleton lazyStaticSingleton1 = (LazyStaticSingleton) SerializationUtils.deserialize(serialize4);
        System.out.println(lazyStaticSingleton == lazyStaticSingleton1);

    }
}
