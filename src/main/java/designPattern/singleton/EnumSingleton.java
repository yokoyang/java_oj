package designPattern.singleton;

public enum EnumSingleton {
    INSTANCE;
    int value;

    //这里可以自定义构造函数
    EnumSingleton() {
        value = 1;
        System.out.println("INSTANCE Create");
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static EnumSingleton getEnumSingleton() {
        return INSTANCE;
    }
}

