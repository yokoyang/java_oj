package designPattern.singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumAttackTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        EnumSingleton enumSingleton = EnumSingleton.getEnumSingleton();
        //序列化攻击
        byte[] serialize4 = SerializationUtils.serialize(enumSingleton);
        EnumSingleton enumSingleton2 = (EnumSingleton) SerializationUtils.deserialize(serialize4);
        System.out.println(enumSingleton == enumSingleton2);
//
//        //反射攻击
//        Constructor<EnumSingleton> enumSingletonConstructor = EnumSingleton.class.getDeclaredConstructor();
//        enumSingletonConstructor.setAccessible(true);
//        EnumSingleton enumSingleton1 = enumSingletonConstructor.newInstance();
//        System.out.println(enumSingleton == enumSingleton1);
    }
}
