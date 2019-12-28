package designPattern.singleton;

public class EnumSingletonTest {
    public static void main(String[] args) {
        System.out.println("start");
        EnumSingleton singleton = EnumSingleton.INSTANCE;
        System.out.println(singleton.getValue());
        singleton.setValue(2);
        System.out.println(singleton.getValue());

    }
}
